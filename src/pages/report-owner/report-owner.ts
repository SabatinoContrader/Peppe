import { ReportProvider } from './../../providers/report/report';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { NgForm } from '../../../node_modules/@angular/forms';

/**
 * Generated class for the ReportOwnerPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-report-owner',
  templateUrl: 'report-owner.html',
})
export class ReportOwnerPage {
  public longitude: number;
  public latitude: number;

  constructor(public navCtrl: NavController, public navParams: NavParams, private reportProvider: ReportProvider) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ReportOwnerPage');
  }

  openReportHystory(): void{
    this.navCtrl.push("ReportHystoryPage");
  }

  sendReport(f: NgForm): void {
    
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
          this.latitude = position.coords.latitude;
          this.longitude = position.coords.longitude;
          console.log("lat al send " +this.latitude);
          console.log("long al send " +this.longitude);

          this.reportProvider.sendReport(f.value.description, "0", 41.1346778, 14.780854199999999,null).subscribe((response) => {
              console.log("RISPOSTA: " + response);
              this.navCtrl.push("HomeOwnerPage");
          });
        });
      } else {
        console.log("Geolocation is not working");
      }


    }
}
