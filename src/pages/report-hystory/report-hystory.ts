import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { DomSanitizer } from '../../../node_modules/@angular/platform-browser';
import { ReportProvider } from '../../providers/report/report';
import { Report } from '../../models/Report';
import { User } from '../../models/User';

/**
 * Generated class for the ReportHystoryPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-report-hystory',
  templateUrl: 'report-hystory.html',
})
export class ReportHystoryPage {

  map: Map<string, string> = new Map<string, string>();

  reports: Array<Report>;

  constructor(private reportProvider: ReportProvider, public navCtrl: NavController, public navParams: NavParams, private sanitizer: DomSanitizer) {
  }

  ionViewWillEnter() {
    console.log('ionViewDidLoad ReportHystoryPage');

    this.map.set('0', "Avviso del gestore");
    this.map.set('1', "Abuso spazio dedicato a persone con disabilità");
    this.map.set('2', "Disservizio stradale");
    this.map.set('3', "Problema riscontrato nell'usufruire del servizio");

    this.onOpenReportHystory();
  }

  onOpenReportHystory(): void{
    this.reportProvider.onOpenHystory().subscribe((response) => {
        console.log(response);
        this.reports = response;

        this.reports.forEach(report => {
          report.media = <string>this.sanitizer.bypassSecurityTrustUrl(report.media);
        });

    });}

  back(): void
  {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    if(user.type == 0) this.navCtrl.push("HomeOwnerPage"); // non servirà più
    if(user.type == 1) this.navCtrl.push("HomeDriverPage");
  }

}
