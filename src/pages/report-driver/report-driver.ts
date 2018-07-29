import { Component, ViewChild, ElementRef } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { NgForm } from '../../../node_modules/@angular/forms';
import { ReportProvider } from '../../providers/report/report';

/**
 * Generated class for the ReportDriverPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-report-driver',
  templateUrl: 'report-driver.html',
})
export class ReportDriverPage {

  public longitude: number;
  public latitude: number;
  selectedFile: File = null;

  constructor(private reportProvider: ReportProvider, public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewWillEnter() {
    console.log('ionViewDidLoad ReportDriverPage');
  }

  sendReport(f: NgForm): void {
    console.log("reportType:" + f.value.select);
    
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
          this.latitude = position.coords.latitude;
          this.longitude = position.coords.longitude;
          console.log("lat al send " +this.latitude);
          console.log("long al send " +this.longitude);

          this.reportProvider.sendReport(f.value.description, f.value.select, this.latitude, this.longitude, this.selectedFile).subscribe((response) => {
              console.log("RISPOSTA: " + response);
              this.navCtrl.push("HomeDriverPage");
          });
        });
      } else {
        console.log("Geolocation is not working");
      }
  
    

  }

  onFileSelected(event){
      this.selectedFile = <File>event.target.files[0];

  }
  
  openReportHystory(): void{
    this.navCtrl.push("ReportHystoryPage");
  }



}
