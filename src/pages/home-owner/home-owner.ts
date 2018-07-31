import { ManagementCarplace } from './../../models/ManagementCarplace';
import { PaymentProvider } from './../../providers/payment/payment';
import { GoogleMapProvider } from './../../providers/google-map/google-map';
//previene la visualizzazione dell'errore Cannot find namespace "google",
//togliendo questa riga l'errore appare ma in realtà la libreria è vista e funziona correttamente.
/// <reference path="../../../node_modules/@types/googlemaps/index.d.ts" />

import { Component, ViewChild, ElementRef, NgZone, ChangeDetectorRef } from '@angular/core';
import { NavController, AlertController, IonicPage, ModalController, MenuController } from 'ionic-angular';
import { Car } from '../../models/Car';
import { CarProvider } from '../../providers/car/car';
import { FormControl } from '../../../node_modules/@angular/forms';
import { Marker, InfoWindow } from '../../../node_modules/@agm/core/services/google-maps-types';
import { MapsAPILoader } from '../../../node_modules/@agm/core';

/**
 * Generated class for the HomeOwnerPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */
declare var google: any;

@IonicPage()
@Component({
  selector: 'page-home-owner',
  templateUrl: 'home-owner.html',
})
export class HomeOwnerPage {

  public AllCarsList: Array<Car>;
  public selectedCar: Car;

  public searchControl: FormControl;
  public lat: number;
  public lng: number;
  public zoom: number;
  private currentLatitude: number;
  private currentLongitude: number;

  carsList: Array<Car>;

  private iconBase: string = 'https://maps.google.com/mapfiles/kml/shapes/';

  private icons;

  private markers = [];
  private currentSelectedMarker: Marker;
  private infoWindow: InfoWindow;
  private markerMap = new Map();
  private freeCarPlaces: number = 0;
  private map: any;

  public isSlotSelected: boolean = false;
  public CurrentManagementCarplace: ManagementCarplace;
  public exceedingInStot: string;

  @ViewChild("seleziona")
  public TurnByTurnElementRef: ElementRef;

  @ViewChild("myposition")
  public MyPositionElementRef: ElementRef;

  public newPrice: string = "Prezzo: ";

  public slotAddress: string = "Slot: ";

  @ViewChild("payandgo")
  public PayAndGoElementRef: ElementRef;

  @ViewChild("carSelect")
  public SelectCarElementRef: ElementRef;

  managementCarplacesList: Array<ManagementCarplace>;

  constructor(private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone,
    private googleMapsProvider: GoogleMapProvider,
    private paymentProvider: PaymentProvider,
    private carProvider: CarProvider,
    private alertCtrl: AlertController,
    private navCtrl: NavController,
    private modalCtrl: ModalController,
    private menuCtrl: MenuController,
    private googleMapProvider: GoogleMapProvider,
    private ref: ChangeDetectorRef
  ) { }


  ionViewWillEnter() {
    this.menuCtrl.enable(true);
    console.log('ionViewDidLoad HomeOwnerPage');

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

    this.updateSlots();

  }

  ionViewDidEnter() {
    this.searchControl = new FormControl();

    //tutti gli oggetti di google.maps vanno istanziati solo dopo il load()
    this.mapsAPILoader.load().then(() => {
      this.infoWindow = new google.maps.InfoWindow();


    });
  }

  updateSlots(): void {
    this.googleMapsProvider.getUpdatedSlots().subscribe((response) => {
      this.managementCarplacesList = response;
      this.DrawSlots(response);
    });
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



        });

      });
    } else {
      console.log("Geolocation is not supported by this browser.");
    }

  }


  DrawSlots(objDTO): void {
    // rimuovo i markers precedenti
    var self = this;
    self.deleteMarkers();

    var title = [];
    for (var i = 0; i < objDTO.length; i++) {
      var obj = objDTO[i];

      var latLng = new google.maps.LatLng(
        obj.slot.latitude, obj.slot.longitude);
      var freeCarPlaces = obj.slot.number_carplace_free;
      var numberCarPlaces = obj.slot.number_carplace;
      var abusiveNumber = (obj.slot.number_carplace - obj.slot.number_carplace_free) - obj.stop_list.length;
      var hasOneExpired = false;
      for (var j = 0; j < obj.stop_list.length; j++) {
        if (obj.stop_list[j].expired === 1) {
          hasOneExpired = true;
          break;
        }
      }

      var info = "<h3>" + obj.slot.address + "</h3>"
        + "<br> Tariffa oraria: " + obj.slot.price + "\u20AC"
        + "<br> Numero posti: " + numberCarPlaces
        + "<br> Disponibli: " + freeCarPlaces
        + "<br><a id='slotInfo'>Dettagli</a>";

      title[i] = info;

      // make markers
      if (abusiveNumber > 0 || hasOneExpired === true)
        var marker = self.makeMarker(latLng, self.icons.parkingRed.icon);
      else
        var marker = self.makeMarker(latLng, self.icons.parkingGreen.icon);

      self.markerMap.set(marker, obj);
      self.AddMarkerEvent(marker, title[i]);
    }

    // se parto da lontano i marker devono essere invisibili se da vicino
    // visibili.
    // self.deleteMarkersZoom();
  }

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

      document.getElementById("slotInfo").addEventListener("click", function () {
        var obj = self.markerMap.get(self.currentSelectedMarker);

        const modal = self.modalCtrl.create("SlotDetailsPage", obj);
        modal.present();

      });
    });
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
      this.clearMarkers();
    } else {
      this.showMarkers();
    }
  };

}
