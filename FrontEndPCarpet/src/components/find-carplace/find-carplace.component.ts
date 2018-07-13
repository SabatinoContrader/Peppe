
//previene la visualizzazione dell'errore Cannot find namespace "google",
//togliendo questa riga l'errore appare ma in realtà la libreria è vista e funziona correttamente.
/// <reference path="../../../node_modules/@types/googlemaps/index.d.ts" />

//import {  } from '@types/googlemaps';

import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { FormControl } from '../../../node_modules/@angular/forms';
import { MapsAPILoader } from '../../../node_modules/@agm/core';
import { Marker, InfoWindow } from '../../../node_modules/@agm/core/services/google-maps-types';
import { GoogleMapService } from '../../services/google-map.service';
import { Slot } from '../../models/Slot';

//import {  } from '@types/googlemaps';
//import {} from '../../../node_modules/@types/google.maps/index';




declare var google: any;

@Component({
  selector: 'app-find-carplace',
  templateUrl: './find-carplace.component.html',
  styleUrls: ['./find-carplace.component.css']
})

export class FindCarplaceComponent implements OnInit {
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

  isInSearchDirectionMode: boolean = false;

  private iconBase: string = 'https://maps.google.com/mapfiles/kml/shapes/';
  private icons = {
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
      //scaledSize: new google.maps.Size(25, 25)
    }
  };

  private markers = [];
  private currentSelectedMarker: Marker;
  private infoWindow: InfoWindow;
  private markerMap = new Map();
  private freeCarPlaces: number = 0;
  private map: google.maps.Map;


  //g: GoogleApiManagerDriver = new GoogleApiManagerDriver('map', 41.9, 12.48,'/api/updateParkings');

  @ViewChild("search")
  public searchElementRef: ElementRef;

  constructor(private mapsAPILoader: MapsAPILoader, private ngZone: NgZone, private googleMapsService: GoogleMapService) { }

  ngOnInit() {
    this.zoom = 10;
    this.lat = 41.9;
    this.lng = 12.48;


    this.searchControl = new FormControl();
    // this.g.selectAutoCompleteTextbox('autocomplete','startsearch');
    // this.g.selectDirectionModeBackButton('cambia');
    // this.g.selectRunTurnByTurnButton('seleziona');
    // this.g.selectChangeMinute('select','newprice','slot','payandgo','carSelect');
    // this.g.selectFindMyPosition('myposition');

    var self = this;
    //tutti gli oggetti di google.maps vanno istanziati solo dopo il load()
    this.mapsAPILoader.load().then(() => {
      // this.mapsAPILoader.disableDefaultUI: true,
      //        zoomControl: true
      this.infoWindow = new google.maps.InfoWindow();
      this.directionsService = new google.maps.DirectionsService();

      let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement);
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

          //set latitude, longitude and zoom
          this.lat = place.geometry.location.lat();
          this.lng = place.geometry.location.lng();
          this.zoom = 15;

          this.googleMapsService.getNearSlots(this.lat, this.lng).subscribe((response) => {
            this.DrawSlots(response);
          });
          
        });
      });

    });
  }

  mapReady(map): void {
    this.map = map;
    var self = this;
    google.maps.event.addListener(self.map, "dragend", function (event) {
      if (self.isInSearchDirectionMode == false) {
        self.googleMapsService.getNearSlots(self.map.getCenter().lat(), self.map.getCenter().lng()).subscribe((response) => {
          self.DrawSlots(response);
        });
      }
    });
    google.maps.event.addListener(this.map, "zoom_changed", function (event) {
      if (this.isInSearchDirectionMode == false)
        this.deleteMarkersZoom();
    });

  //   document.getElementById("").addEventListener("click", function () {
  //     self.isInSearchDirectionMode = false;
  //     self.directionsRenderer.setMap(null);
  //     self.directionsRenderer = null;
  //     //self.directionModeBackButtonDOM.disabled = true;
  //     //self.runTurnByTurnButtonDOM.disabled = true;
  //     self.deleteMarkers();
  //     //self.geocodeCoordinates(self.DirectionModeStartLatitude, self.DirectionModeStartLongitude);
  // });


  }
  
  //CHIAMATA AJAX SLOT VICINI
  DrawSlots(objlist): void {
    var self = this;

    // rimuovo i markers precedenti
    self.deleteMarkers();

    // var infoWindow = new google.maps.InfoWindow(), marker, i;

    var title = [];
    for (var i = 0; i < objlist.length; i++) {
      var obj = objlist[i];

      var latLng = new google.maps.LatLng(
        obj.latitude, obj.longitude);
      self.freeCarPlaces = obj.number_carplace_free;
      var numberCarPlaces = obj.number_carplace;
      var info = "<h3>" + obj.address + "</h3>"
        + "<br> Tipo: " + obj.type
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
        document.getElementById("sosta").addEventListener("click", function () {
          //self.StartStop(marker);
        });
      }

    });
  };

  // Removes the markers from the map, but keeps them in the array.
  clearMarkers(): void {
    //var self = this;
    this.setMapOnAll(null);
  };

  // Sets the map on all markers in the array.
  setMapOnAll(map): void {
    //var self = this;
    for (var i = 0; i < this.markers.length; i++) {
      this.markers[i].setMap(map);
    }
  };


  // Shows any markers currently in the array.
  showMarkers(): void {
    //var self = this;
    this.setMapOnAll(this.map);
  };


  // Hidden markers when zooming to far.
  deleteMarkersZoom(): void {
    //var self = this;
    if (this.map.getZoom() < 15) {
      console.log("zoom: " + this.map.getZoom());
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
      // self.runTurnByTurnButtonDOM.disabled = false;
      // self.directionModeBackButtonDOM.disabled = false;
  };


  //METODI DRAG E ZOOM
  InitGoogleMapsEvents(): void {

    google.maps.event.addListener(this.map, "dragend", function (event) {
      if (this.isInSearchDirectionMode == false)
        this.doAjaxForNearSlots(this.map.getCenter().lat(), this.map.getCenter().lng());
    });
    google.maps.event.addListener(this.map, "zoom_changed", function (event) {
      if (this.isInSearchDirectionMode == false)
        this.deleteMarkersZoom();
    });
  };



}







//AJAX GENERICA (PER ORA USATA PER PAGAMENTI)
//     doAjax(url, mapParams, success, fail, method): void {
//         var self = this;
//         var http = new XMLHttpRequest();
//         var myurl = url;

//         var params = "";
//         for (const [key, value] of mapParams.entries()) {
//             console.log(key, value);
//             params = params + (key + "=" + value + "&");
//         }
//         if (params.length > 0) params.slice(0, -1);

//         console.log("params: " + params);

//         http.open(method, myurl, true);
//         // Send the proper header information along with the request
//         http.setRequestHeader('Content-type',
//             'application/x-www-form-urlencoded');
//         if (params.length > 0)
//             http.send(params);
//         else
//             http.send();
//         http.onreadystatechange = function () {
//             if (http.readyState == 4 && http.status == 200) {
//                 success(http);
//             }
//             else {
//                 fail(http);
//             }
//         }
//     };