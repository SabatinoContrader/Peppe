//previene la visualizzazione dell'errore Cannot find namespace "google",
//togliendo questa riga l'errore appare ma in realtà la libreria è vista e funziona correttamente.
/// <reference path="../../../../../../node_modules/@types/googlemaps/index.d.ts" />

import { Component, OnInit, ViewChild, ElementRef, NgZone, ApplicationRef, ChangeDetectorRef } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MapsAPILoader } from '@agm/core';
import { Marker, InfoWindow } from '@agm/core/services/google-maps-types';
import { Car } from '../../../../_models/Car';
import { GoogleMapService } from '../../../../_services/google-map.service';
import { PaymentService } from '../../../../_services/payment.service';
import { BookService } from './../../../../_services/book.service';
import { Slot } from '../../../../_models/Slot';


declare var google: any;

@Component({
    selector: 'app-find-carplace',
    templateUrl: './find-carplace.component.html',
    styleUrls: ['./find-carplace.component.scss']
})

export class FindCarplaceComponent implements OnInit {
    public searchControl: FormControl;
    public lat: number;
    public lng: number;
    public zoom: number;
    private currentLatitude: number;
    private currentLongitude: number;

    dateTimePicker;

    parklist;

    directionsService = null;
    directionsRenderer = null;

    DirectionModeStartLatitude;
    DirectionModeStartLongitude;

    carsList: Array<Car>;

    isInSearchDirectionMode: boolean = false;

    private iconBase: string = 'https://maps.google.com/mapfiles/kml/shapes/';

    private icons;

    public slots: Array<Slot>;
    flagMapAndInfo = false;
    flagIniziaSosta = false;
    flagPrenotaSosta = false;

    private markers = [];
    private currentSelectedMarker: Marker;
    private infoWindow: InfoWindow;
    private markerMap = new Map();
    private freeCarPlaces: number = 0;
    private map: google.maps.Map;

    @ViewChild("search")
    public searchElementRef: ElementRef;

    @ViewChild("cambia")
    public DirectionModeBackElementRef: ElementRef;

    @ViewChild("seleziona")
    public TurnByTurnElementRef: ElementRef;

    @ViewChild("myposition")
    public MyPositionElementRef: ElementRef;

    //Seleziona pagamento per sosta
    @ViewChild("select")
    public SelectTimeElementRef: ElementRef;

    @ViewChild("selectTimeBook")
    public SelectTimeBookElementRef: ElementRef;


    public newPrice: string = "Prezzo: ";

    public slotAddress: string = "Slot: ";

    @ViewChild("payandgo")
    public PayAndGoElementRef: ElementRef;

    @ViewChild("carSelect")
    public SelectCarElementRef: ElementRef;

    addressToPass;


    constructor(private mapsAPILoader: MapsAPILoader,
        private ngZone: NgZone,
        private googleMapsService: GoogleMapService,
        private paymentService: PaymentService,
        private ref: ChangeDetectorRef,
        private bookService: BookService
    ) { }

    ngOnInit() {

        this.googleMapsService.getNotStopCarList().subscribe((response) => {
            this.carsList = response;
        });

        this.zoom = 10;
        this.lat = 41.9;
        this.lng = 12.48;


        this.searchControl = new FormControl();

        var self = this;
        //tutti gli oggetti di google.maps vanno istanziati solo dopo il load()
        this.mapsAPILoader.load().then(() => {
            this.infoWindow = new google.maps.InfoWindow();
            this.directionsService = new google.maps.DirectionsService();


            let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement);
            this.addressToPass = this.searchElementRef.nativeElement;
            // , {
            //   types: ["address"]
            //});

            autocomplete.addListener("place_changed", () => {
                this.ngZone.run(() => {
                    //get the place result
                    let place: google.maps.places.PlaceResult = autocomplete.getPlace();

                    //verify result
                    if (place.geometry === undefined || place.geometry === null) {
                        return;
                    }
                    this.flagMapAndInfo = false;

                    //set latitude, longitude and zoom
                    this.lat = place.geometry.location.lat();
                    this.lng = place.geometry.location.lng();
                    this.zoom = 15;

                    // this.googleMapsService.getNearSlots(this.lat, this.lng, this.SelectCarElementRef.nativeElement.value).subscribe((response) => {
                    //     this.DrawSlots(response);
                    //     console.log("CIAOOOOO: "+response);
                    // });



                });
            });


            var self = this;
            self.searchElementRef.nativeElement.addEventListener('keydown',
                function(event) {
                    // keycode 13 = Enter
                    if (event.keyCode === 13) {
                        event.preventDefault();



                        self.ngZone.run(() => {
                            //get the place result
                            let place: google.maps.places.PlaceResult = autocomplete.getPlace();

                            //verify result
                            if (place.geometry === undefined || place.geometry === null) {
                                return;
                            }

                            //set latitude, longitude and zoom
                            self.lat = place.geometry.location.lat();
                            self.lng = place.geometry.location.lng();
                            self.zoom = 15;

                            // self.googleMapsService.getNearSlots(self.lat, self.lng, self.SelectCarElementRef.nativeElement.value).subscribe((response) => {
                            //     self.DrawSlots(response);
                            //     this.parkList = response;
                            // });

                        });


                    }
                });





        });
    }

    startStop(){
       
            var selectedcar = this.SelectCarElementRef.nativeElement.value;
            if (selectedcar != "") {
                // this.SelectTimeElementRef.nativeElement.disabled = true;
                // this.PayAndGoElementRef.nativeElement.disabled = true;

                var obj = this.markerMap.get(this.currentSelectedMarker);


                var timeToAddFromNow = this.SelectTimeElementRef.nativeElement.value;
                var price = (obj.price / 60) * timeToAddFromNow;

                 console.log('timeToAdd: ' + timeToAddFromNow);
                // console.log('totalPrice: ' + price);
                // console.log('id_slot: ' + obj.id);
                // console.log('id_car: ' + selectedcar);

                this.paymentService.addPayment(price, obj.id, selectedcar, timeToAddFromNow).subscribe((response) => {

                    var index = this.carsList.findIndex((car) => { return car.id == response });
                    if (index > -1) {
                        this.carsList.splice(index, 1);
                    }
                    console.log("pagamento effettuato");
                });
                this.flagIniziaSosta = false;

            } else { alert("Devi inserire un auto prima di iniziare la sosta!"); }

        
    }

    dateChanged(val: any) {
        console.log('Date changed', val);
        this.dateTimePicker = val;
      }

    startBook(){
       
        var selectedcar = this.SelectCarElementRef.nativeElement.value;
        if (selectedcar != "") {
            // this.SelectTimeElementRef.nativeElement.disabled = true;
            // this.PayAndGoElementRef.nativeElement.disabled = true;

            var obj = this.markerMap.get(this.currentSelectedMarker);


            var timeToAddFromNow = this.SelectTimeBookElementRef.nativeElement.value;
            var price = (obj.price / 60) * timeToAddFromNow;

             console.log('timeToAdd: ' + timeToAddFromNow);
            // console.log('totalPrice: ' + price);
            // console.log('id_slot: ' + obj.id);
            // console.log('id_car: ' + selectedcar);

            this.bookService.addBook(this.dateTimePicker.value, timeToAddFromNow, obj.id, selectedcar, price).subscribe((response) => {

                var index = this.carsList.findIndex((car) => { return car.id == response });
                if (index > -1) {
                    this.carsList.splice(index, 1);
                }
                console.log("prenotazione effettuata");
            });
            this.flagPrenotaSosta = false;

        } else { alert("Devi inserire un auto prima di iniziare la sosta!"); }

    
}

changeMinute(){
    
}

    findPark() {
        this.flagMapAndInfo = true;
        this.googleMapsService.getNearSlots(this.lat, this.lng, this.SelectCarElementRef.nativeElement.value).subscribe((response) => {
            this.DrawSlots(response);
            this.slots = response;
        });
    }

    mapReady(map): void {
        this.map = map;


        this.icons = {
            parking: {
                url: this.iconBase + 'parking_lot_maps.png'
            },
            parkingGreen: {
                icon: '/assets/green.png'
            },
            parkingRed: {
                icon: '/assets/red.png'
            },
            parkingYellow: {
                icon: '/assets/yellow.png'
            },
            start: {
                url: this.iconBase + 'cabs.png',
                scaledSize: new google.maps.Size(25, 25)
            }
        };





        var self = this;
        google.maps.event.addListener(self.map, "dragend", function(event) {
            if (self.isInSearchDirectionMode == false) {

                self.lat = self.map.getCenter().lat();
                self.lng = self.map.getCenter().lng();

                self.googleMapsService.getNearSlots(self.map.getCenter().lat(), self.map.getCenter().lng(), self.SelectCarElementRef.nativeElement.value).subscribe((response) => {
                    self.DrawSlots(response);
                });
            }
        });
        google.maps.event.addListener(this.map, "zoom_changed", function(event) {
            self.zoom = self.map.getZoom();
            if (self.isInSearchDirectionMode == false)
                self.deleteMarkersZoom();
        });

        self.DirectionModeBackElementRef.nativeElement.addEventListener("click", function() {
            self.isInSearchDirectionMode = false;
            self.directionsRenderer.setMap(null);
            self.directionsRenderer = null;
            self.DirectionModeBackElementRef.nativeElement.disabled = true;
            self.TurnByTurnElementRef.nativeElement.disabled = true;
            self.deleteMarkers();
            self.ngZone.run(() => {

                self.zoom = 15;
                self.lat = self.DirectionModeStartLatitude;
                self.lng = self.DirectionModeStartLongitude;

                self.googleMapsService.getNearSlots(self.DirectionModeStartLatitude, self.DirectionModeStartLongitude, self.SelectCarElementRef.nativeElement.value).subscribe((response) => {
                    self.DrawSlots(response);
                });

            });

        });


        self.SelectTimeElementRef.nativeElement.addEventListener('change', function() {
            var obj = self.markerMap.get(self.currentSelectedMarker);
            var min = self.SelectTimeElementRef.nativeElement.value;
            var pay = (obj.price / 60) * min;
            //self.showPriceDOM.innerHTML = "Prezzo: " + pay + "\u20AC";
            self.newPrice = "Prezzo: " + pay + "\u20AC";
        });

    


    }

    getPosition() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                this.currentLatitude = position.coords.latitude;
                this.currentLongitude = position.coords.longitude;

                this.lat = position.coords.latitude;
                this.lng = position.coords.longitude;

                this.ngZone.run(() => {

                    this.zoom = 15;
                    this.lat = this.currentLatitude;
                    this.lng = this.currentLongitude;

                    // this.googleMapsService.getNearSlots(this.currentLatitude, this.currentLongitude, this.SelectCarElementRef.nativeElement.value).subscribe((response) => {
                    //     this.DrawSlots(response);
                    // });

                });

            },
                error => {
                    console.error("Hello! This is your FBI agent bro, please enable location so we can track you. Thanks fam", error);
                });
        } else {
            console.log("Geolocation is not supported by this browser.");
        }

    }

    DrawSlots(objlist): void {
        var self = this;
        self.ref.detectChanges();
        // rimuovo i markers precedenti
        self.deleteMarkers();

        var title = [];
        for (var i = 0; i < objlist.length; i++) {
            var obj = objlist[i];

            var latLng = new google.maps.LatLng(
                obj.latitude, obj.longitude);
            self.freeCarPlaces = obj.number_carplace_free;
            var numberCarPlaces = obj.number_carplace;
            var type;
            if (obj.type == 0)
                type = "Pubblico";
            else
                type = "Privato";
            var info = "<h3>" + obj.address + "</h3>"
                + "<br> Tipo: " + type
                + "<br> Tariffa oraria: " + obj.price + "\u20AC"
                + "<br> Numero posti: " + numberCarPlaces
                + "<br> Disponibli: " + self.freeCarPlaces
                + "<br><a id='indications'>Indicazioni</a>";

            if (self.freeCarPlaces > 0)
                info += "<br><a id='sosta' style=color:green>Inizia sosta</a>";

            if (obj.type == 1)
                info = info + "<br><a id='prenota' style=color:blue>Prenota</a>";

            title[i] = info;

            // make markers
            var marker;
            if (self.freeCarPlaces == 0)
                marker = self.makeMarker(latLng, self.icons.parkingRed.icon);
            else if (self.freeCarPlaces < 5)
                marker = self.makeMarker(latLng, self.icons.parkingYellow.icon);
            else
                marker = self.makeMarker(latLng, self.icons.parkingGreen.icon);
            self.markerMap.set(marker, obj);
            self.AddMarkerEvent(marker, title[i]);
        }


    };

    // Deletes all markers in the array by removing references to them.
    deleteMarkers(): void {
        var self = this;
        self.clearMarkers();
        self.markers = [];
        self.markerMap.clear();
    };

    makeMarker(position, icon): any {
        var marker = new google.maps.Marker({
            position: position,
            map: this.map,
            icon: icon
        });
        this.markers.push(marker);
        return marker;
    };

    AddMarkerEvent(marker, content): void {
        var self = this;
        google.maps.event.addListener(marker, 'click',
            (function(marker, content) {

                return function() {
                    self.currentSelectedMarker = marker;

                    google.maps.event.clearListeners(self.infoWindow, 'domready');
                    self.infoWindow.setContent(content);
                    self.InitInfoWindowEvents(marker);
                    self.infoWindow.open(this.map, marker);
                }
            })(marker, content));
    };


    InitInfoWindowEvents(marker): void {
        var self = this;
        google.maps.event.addListener(self.infoWindow, 'domready', function() {

            document.getElementById("indications").addEventListener("click", function() {

                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(showPosition,
                        error => {
                            console.error("Hi, your very own personal FBI agent here. Do you mind if I track your position? I'm just worried about your wellbeing. Stay safe now!", error);
                        });
                } else {
                    console.log("Geolocation is not supported by this browser.");
                }

                function showPosition(position) {
                    self.currentLatitude = position.coords.latitude;
                    self.currentLongitude = position.coords.longitude;
                    self.StartDirectionsRequest(marker);
                }
            });

            var obj = self.markerMap.get(self.currentSelectedMarker);
            if (obj.number_carplace_free > 0) {
                // call selectChangeMinute() before
                document.getElementById("sosta").addEventListener("click", () => {
                    self.flagPrenotaSosta = false;
                    self.flagIniziaSosta = true;
                    self.StartStop(marker);
                });
            }
            if (obj.type == 1) {
                // call selectChangeMinute() before
                document.getElementById("prenota").addEventListener("click", () => {
                    self.flagIniziaSosta = false;
                    self.flagPrenotaSosta = true;
                    self.StartStop(marker);
                });
            }

        });
    };

    StartStop = (marker): void => {

        var self = this;
        var obj = self.markerMap.get(self.currentSelectedMarker);

        self.SelectTimeElementRef.nativeElement.value = 15;

        self.slotAddress = "Slot: " + obj.address + "";

        var min = self.SelectTimeElementRef.nativeElement.value;
        var pay = obj.price;
        self.newPrice = "Prezzo orario: " + pay + "\u20AC";

        self.SelectTimeElementRef.nativeElement.disabled = false;
        self.PayAndGoElementRef.nativeElement.disabled = false;

        //a quanto pare siamo fuori da ngZone e quindi dobbiamo dirgli manualmente di leggere i cambiamenti
        this.ref.detectChanges();
    }


    // Removes the markers from the map, but keeps them in the array.
    clearMarkers(): void {
        this.setMapOnAll(null);
    };

    // Sets the map on all markers in the array.
    setMapOnAll(map): void {
        for (var i = 0; i < this.markers.length; i++) {
            this.markers[i].setMap(map);
        }
    };


    // Shows any markers currently in the array.
    showMarkers(): void {
        this.setMapOnAll(this.map);
    };


    // Hidden markers when zooming to far.
    deleteMarkersZoom(): void {
        if (this.map.getZoom() < 15) {
            //console.log("zoom: " + this.map.getZoom());
            this.clearMarkers();
        } else {
            this.showMarkers();
        }
    };

    StartDirectionsRequest(marker): void {

        var from = new google.maps.LatLng(this.currentLatitude, this.currentLongitude);
        var to = new google.maps.LatLng(marker.getPosition().lat(), marker.getPosition().lng());

        var directionsRequest = {
            origin: from,
            destination: to,
            travelMode: google.maps['DirectionsTravelMode'].DRIVING,
            unitSystem: google.maps.UnitSystem.METRIC
        };

        var self = this;
        this.directionsService.route(
            directionsRequest,
            function(response, status) {

                if (status == google.maps.DirectionsStatus.OK) {
                    self.directionsRenderer = new google.maps.DirectionsRenderer({
                        map: self.map,
                        directions: response,
                        suppressMarkers: true

                    });
                    self.deleteMarkers();
                }
                else {
                    alert("Unable to retrive route");
                }
                var leg = response.routes[0].legs[0];
                self.makeMarker(leg.start_location, self.icons.start);
                self.makeMarker(leg.end_location, self.icons.parking);
            }
        );

        self.DirectionModeStartLatitude = marker.getPosition().lat();
        self.DirectionModeStartLongitude = marker.getPosition().lng();

        self.isInSearchDirectionMode = true;
        self.TurnByTurnElementRef.nativeElement.disabled = false;
        self.DirectionModeBackElementRef.nativeElement.disabled = false;
    };

    changeCar(): void {
        this.googleMapsService.getNearSlots(this.lat, this.lng, this.SelectCarElementRef.nativeElement.value).subscribe((response) => {
            this.DrawSlots(response);
        });

    }

}