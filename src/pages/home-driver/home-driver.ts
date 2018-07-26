import { PaymentProvider } from './../../providers/payment/payment';
import { GoogleMapProvider } from './../../providers/google-map/google-map';
//previene la visualizzazione dell'errore Cannot find namespace "google",
//togliendo questa riga l'errore appare ma in realtà la libreria è vista e funziona correttamente.
/// <reference path="../../../node_modules/@types/googlemaps/index.d.ts" />

import { Component, ViewChild, ElementRef, NgZone, ChangeDetectorRef } from '@angular/core';
import { NavController, AlertController, IonicPage } from 'ionic-angular';
import { Car } from '../../models/Car';
import { CarProvider } from '../../providers/car/car';
import { FormControl } from '../../../node_modules/@angular/forms';
import { Marker, InfoWindow } from '../../../node_modules/@agm/core/services/google-maps-types';
import { MapsAPILoader } from '../../../node_modules/@agm/core';

/**
 * Generated class for the HomeDriverPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
declare var google: any;

@IonicPage()
@Component({
  selector: 'page-home-driver',
  templateUrl: 'home-driver.html',
})
export class HomeDriverPage {

  public AllCarsList: Array<Car>;
  private selectedCar: Car;

  public searchControl: FormControl;
  public lat: number;
  public lng: number;
  public zoom: number;
  private currentLatitude: number;
  private currentLongitude: number;

  directionsService = null;
  directionsRenderer = null;

  DirectionModeStartLatitude;
  DirectionModeStartLongitude;

  carsList: Array<Car>;

  isInSearchDirectionMode: boolean = false;

  private iconBase: string = 'https://maps.google.com/mapfiles/kml/shapes/';

  private icons;

  private markers = [];
  private currentSelectedMarker: Marker;
  private infoWindow: InfoWindow;
  private markerMap = new Map();
  private freeCarPlaces: number = 0;
  private map: google.maps.Map;

  // @ViewChild("search")
  // public searchElementRef: ElementRef;

  @ViewChild("cambia")
  public DirectionModeBackElementRef: ElementRef;

  @ViewChild("seleziona")
  public TurnByTurnElementRef: ElementRef;

  @ViewChild("myposition")
  public MyPositionElementRef: ElementRef;

  @ViewChild("parkBtn")
  public parkBtnElementRef: ElementRef;

  //Seleziona pagamento per sosta
  // @ViewChild("select")
  // public SelectTimeElementRef: ElementRef;

  public newPrice: string = "Prezzo: ";

  public slotAddress: string = "Slot: ";

  @ViewChild("payandgo")
  public PayAndGoElementRef: ElementRef;

  @ViewChild("carSelect")
  public SelectCarElementRef: ElementRef;

  constructor(private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private googleMapsProvider: GoogleMapProvider,
    private paymentProvider: PaymentProvider,
    //private ref: ChangeDetectorRef,
    private carProvider: CarProvider,
    private alertCtrl: AlertController,
    private navCtrl: NavController
  ) { }


  //ionViewLoaded
  //<HTMLInputElement>
  //ionViewWillEnter
  ionViewWillEnter() {
    console.log('ionViewDidLoad HomeDriverPage');

    this.carProvider.myCarsList().subscribe(response => {
      this.AllCarsList = response;
    });

    this.googleMapsProvider.getNotStopCarList().subscribe((response) => {
      this.carsList = response;
      if (this.carsList && this.carsList.length > 0)
        this.selectedCar = this.carsList[0];
    });

    this.zoom = 10;
    this.lat = 41.9;
    this.lng = 12.48;

  }

  ionViewDidEnter() {

    console.log("quiiii");
    this.searchControl = new FormControl();

    //var self = this;
    //tutti gli oggetti di google.maps vanno istanziati solo dopo il load()
    this.mapsAPILoader.load().then(() => {
      this.infoWindow = new google.maps.InfoWindow();
      this.directionsService = new google.maps.DirectionsService();

      console.log("quiiii");
      let elem = <HTMLInputElement>document.getElementsByClassName('searchbar-input')[0];
      let autocomplete = new google.maps.places.Autocomplete(elem);
      // , {
      //   types: ["address"]
      //});

      autocomplete.addListener("place_changed", () => {
        this.ngZone.run(() => {
          //get the place result
          console.log("quiiii");
          let place: google.maps.places.PlaceResult = autocomplete.getPlace();

          //verify result
          if (place.geometry === undefined || place.geometry === null) {
            return;
          }

          //set latitude, longitude and zoom
          this.lat = place.geometry.location.lat();
          this.lng = place.geometry.location.lng();
          this.zoom = 15;

          this.googleMapsProvider.getNearSlots(this.lat, this.lng, this.SelectCarElementRef.nativeElement.value).subscribe((response) => {
            this.DrawSlots(response);
          });

        });
      });


      // var self = this;
      // elem.addEventListener('keydown',
      //   function (event) {
      //     // keycode 13 = Enter
      //     if (event.keyCode === 13) {
      //       event.preventDefault();



      //       self.ngZone.run(() => {
      //         //get the place result
      //         let place: google.maps.places.PlaceResult = autocomplete.getPlace();

      //         //verify result
      //         if (place.geometry === undefined || place.geometry === null) {
      //           return;
      //         }

      //         //set latitude, longitude and zoom
      //         self.lat = place.geometry.location.lat();
      //         self.lng = place.geometry.location.lng();
      //         self.zoom = 15;

      //         self.googleMapsProvider.getNearSlots(self.lat, self.lng, self.SelectCarElementRef.nativeElement.value).subscribe((response) => {
      //           self.DrawSlots(response);
      //         });

      //       });


      //     }
      //   });





    });
  }

  openCar(): void {
    console.log('openCar');
    let alert = this.alertCtrl.create({
      enableBackdropDismiss: false,
    });
    alert.setTitle("<b>Le Mie Auto:</b>");

    this.AllCarsList.forEach(car => {
      var carInStop: Car = this.carsList.find(c => c.id == car.id);
      alert.addInput({
        type: 'radio',
        label: car.name + " (" + car.license_plate + ")",
        value: car.id + "",
        checked: (car.id == this.selectedCar.id),
        disabled: carInStop == null
      });


    });

    alert.addButton({
      text: "OK",
      handler: data => {
        console.log("datachecked: " + data);
        this.selectedCar = this.AllCarsList.find(car => car.id == data);
      }
    });
    alert.addButton("ANNULLA");
    alert.addButton({
      text: "AGGIUNGI",
      handler: data => {
        let alert1 = this.alertCtrl.create({
          enableBackdropDismiss: false,
        });
        alert1.setTitle("Aggiungi Auto");
        alert1.addInput({
          name: "license_plate",
          placeholder: "Targa"
        });
        alert1.addInput({
          name: "name",
          placeholder: "Nome"
        });
        alert1.addButton("ANNULLA");
        alert1.addButton({
          text: "AGGIUNGI",
          handler: data => {
            this.carProvider.addNewCar(data.license_plate, data.name).subscribe(response => {
              //La response dovrà essere l'auto creata.
              this.AllCarsList.push(response);
              this.selectedCar = response;
            }
            );
          }
        });
        alert1.present();
      }
    });
    alert.present();
  }

  chooseMinute() {
    let alert2 = this.alertCtrl.create({
      enableBackdropDismiss: false,
    });

    var obj = this.markerMap.get(this.currentSelectedMarker);

    alert2.setTitle("<b>Per quanti minuti vuoi sostare?</b>");

    alert2.addInput({
      type: 'radio',
      label: '15 min ' + '(' + (obj.price / 60) * 15 + "\u20AC" + ')',
      value: '15',
      checked: true
    });

    alert2.addInput({
      type: 'radio',
      label: '30 min ' + '(' + (obj.price / 60) * 30 + "\u20AC" + ')',
      value: '30'
    });

    alert2.addInput({
      type: 'radio',
      label: '45 min ' + '(' + (obj.price / 60) * 45 + "\u20AC" + ')',
      value: '45'
    });

    alert2.addInput({
      type: 'radio',
      label: '1 h ' + '(' + (obj.price / 60) * 60 + "\u20AC" + ')',
      value: '60'
    });

    alert2.addInput({
      type: 'radio',
      label: '1 h 15 min ' + '(' + (obj.price / 60) * 75 + "\u20AC" + ')',
      value: '75'
    });

    alert2.addInput({
      type: 'radio',
      label: '1 h 30 min ' + '(' + (obj.price / 60) * 90 + "\u20AC" + ')',
      value: '90'
    });

    alert2.addInput({
      type: 'radio',
      label: '1 h 45 min ' + '(' + (obj.price / 60) * 105 + "\u20AC" + ')',
      value: '105'
    });

    alert2.addInput({
      type: 'radio',
      label: '2 h ' + '(' + (obj.price / 60) * 120 + "\u20AC" + ')',
      value: '120'
    });

    alert2.addButton({
      text: "OK",
      handler: data => {
        console.log("datachecked: " + data);
        this.payAndGo(data);
      }
    });
    alert2.addButton("ANNULLA");

    alert2.present();
  }

  openMyStops(): void {
    this.navCtrl.push("ExtensionStopsPage");
  }

  mapReady(map): void {
    this.map = map;


    this.icons = {
      parking: {
        url: this.iconBase + 'parking_lot_maps.png'
      },
      parkingGreen: {
        icon: '/assets/icon/green.png'
      },
      parkingRed: {
        icon: '/assets/icon/red.png'
      },
      parkingYellow: {
        icon: '/assets/icon/yellow.png'
      },
      start: {
        url: this.iconBase + 'cabs.png',
        scaledSize: new google.maps.Size(25, 25)
      }
    };





    var self = this;
    google.maps.event.addListener(self.map, "dragend", function (event) {
      if (self.isInSearchDirectionMode == false) {

        self.lat = self.map.getCenter().lat();
        self.lng = self.map.getCenter().lng();

        self.googleMapsProvider.getNearSlots(self.map.getCenter().lat(), self.map.getCenter().lng(), self.SelectCarElementRef.nativeElement.value).subscribe((response) => {
          self.DrawSlots(response);
        });
      }
    });
    google.maps.event.addListener(this.map, "zoom_changed", function (event) {
      self.zoom = self.map.getZoom();
      if (self.isInSearchDirectionMode == false)
        self.deleteMarkersZoom();
    });

    self.DirectionModeBackElementRef.nativeElement.addEventListener("click", function () {
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

        self.googleMapsProvider.getNearSlots(self.DirectionModeStartLatitude, self.DirectionModeStartLongitude, self.SelectCarElementRef.nativeElement.value).subscribe((response) => {
          self.DrawSlots(response);
        });

      });

    });


    // self.SelectTimeElementRef.nativeElement.addEventListener('change', function () {
    //   var obj = self.markerMap.get(self.currentSelectedMarker);
    //   var min = self.SelectTimeElementRef.nativeElement.value;
    //   var pay = (obj.price / 60) * min;
    //   //self.showPriceDOM.innerHTML = "Prezzo: " + pay + "\u20AC";
    //   self.newPrice = "Prezzo: " + pay + "\u20AC";
    // });




  }

  getPosition() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.currentLatitude = position.coords.latitude;
        this.currentLongitude = position.coords.longitude;
        this.ngZone.run(() => {

          this.zoom = 15;
          this.lat = this.currentLatitude;
          this.lng = this.currentLongitude;

          this.googleMapsProvider.getNearSlots(this.currentLatitude, this.currentLongitude, this.SelectCarElementRef.nativeElement.value).subscribe((response) => {
            this.DrawSlots(response);
          });

        });

      });
    } else {
      console.log("Geolocation is not supported by this browser.");
    }

  }

  payAndGo(data) {
    if (this.selectedCar != null) {
      //self.SelectTimeElementRef.nativeElement.disabled = true;
      //self.PayAndGoElementRef.nativeElement.disabled = true;

      var obj = this.markerMap.get(this.currentSelectedMarker);


      //var timeToAddFromNow = self.SelectTimeElementRef.nativeElement.value;
      var price = (obj.price / 60) * data;

      // console.log('timeToAdd: ' + timeToAddFromNow);
      // console.log('totalPrice: ' + price);
      // console.log('id_slot: ' + obj.id);
      // console.log('id_car: ' + selectedcar);

      this.paymentProvider.addPayment(price, obj.id, this.selectedCar.id, data).subscribe((response) => {

        var index = this.carsList.findIndex((car) => { return car.id == response });
        if (index > -1) {
          this.carsList.splice(index, 1);
        }

        if (this.carsList && this.carsList.length > 0)
        this.selectedCar = this.carsList[0];

        console.log("pagamento effettuato");
      });
    } else { alert("Devi inserire un auto prima di iniziare la sosta!"); }

  }

  DrawSlots(objlist): void {
    var self = this;

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
        info += "<br><a id='sosta'>Inizia sosta</a>";

      if (obj.type == "privato")
        info = info + "<br><a>Prenota</a>";

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
      (function (marker, content) {

        return function () {
          self.currentSelectedMarker = marker;

          //self.parkBtnElementRef.nativeElement.disabled = true;
          //document.getElementById("parkBtn").disabled = true;

          google.maps.event.clearListeners(self.infoWindow, 'domready');
          self.infoWindow.setContent(content);
          self.InitInfoWindowEvents(marker);
          self.infoWindow.open(this.map, marker);
        }
      })(marker, content));
  };


  InitInfoWindowEvents(marker): void {
    var self = this;
    google.maps.event.addListener(self.infoWindow, 'domready', function () {

      document.getElementById("indications").addEventListener("click", function () {

        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(showPosition);
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
          //self.StartStop(marker);
        });
      }

    });
  };

  // StartStop = (marker): void => {

  //   var self = this;
  //   var obj = self.markerMap.get(self.currentSelectedMarker);

  //   //self.SelectTimeElementRef.nativeElement.value = 15;

  //   self.slotAddress = "Slot: " + obj.address + "";

  //   //var min = self.SelectTimeElementRef.nativeElement.value;
  //   var pay = (obj.price / 60) * min;
  //   self.newPrice = "Prezzo: " + pay + "\u20AC";

  //   self.SelectTimeElementRef.nativeElement.disabled = false;
  //   self.PayAndGoElementRef.nativeElement.disabled = false;

  //   //a quanto pare siamo fuori da ngZone e quindi dobbiamo dirgli manualmente di leggere i cambiamenti
  //   this.ref.detectChanges();
  // }


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
      function (response, status) {

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
    console.log("wewe");
    this.googleMapsProvider.getNearSlots(this.lat, this.lng, this.SelectCarElementRef.nativeElement.value).subscribe((response) => {
      this.DrawSlots(response);
    });
  }
}
