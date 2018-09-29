import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ReportProvider } from '../../providers/report/report';
import { Report } from '../../models/Report';
import { DomSanitizer } from '../../../node_modules/@angular/platform-browser';
import { User } from '../../models/User';

/**
 * Generated class for the ReportNearPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-report-near',
  templateUrl: 'report-near.html',
})
export class ReportNearPage {

  map: Map<string, string> = new Map<string, string>();
  latitude: number;
  longitude: number;
  reports: Array<Report>;

  currentUser: User;

  constructor(private reportProvider: ReportProvider, public navCtrl: NavController, public navParams: NavParams,  private sanitizer: DomSanitizer) {
  }

  ionViewWillEnter() {

    this.currentUser = JSON.parse(sessionStorage.getItem("user"));

    console.log('ionViewDidLoad ReportNearPage');

    this.map.set('0', "Avviso del gestore");
    this.map.set('1', "Abuso spazio dedicato a persone con disabilità");
    this.map.set('2', "Disservizio stradale");
    this.map.set('3', "Problema riscontrato nell'usufruire del servizio");

    this.onOpenReportNear();
  }

  onOpenReportNear(): void{

    console.log("QUI");
    //use localization to get position
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        console.log("lat al onOpenNear " +this.latitude);
        console.log("long al onOpenNear " +this.longitude);
        this.reportProvider.onOpenNear(this.latitude,this.longitude).subscribe((response) => {
          console.log(response);
          this.reports = response;

          this.reports.forEach(report => {
            report.media = <string>this.sanitizer.bypassSecurityTrustUrl(report.media);
          });

        });
      });
    } else {
      console.log("Geolocation is not working");
    }
  }



}
