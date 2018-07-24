import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Stop } from '../../models/Stop';
import { StopProvider } from '../../providers/stop/stop';

/**
 * Generated class for the ExtensionStopsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-extension-stops',
  templateUrl: 'extension-stops.html',
})
export class ExtensionStopsPage {
  myStopsList: Stop[];

  constructor(public navCtrl: NavController, public navParams: NavParams, private stopProvider: StopProvider) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ExtensionStopsPage');
    this.stopProvider.myStopsList().subscribe(response => {this.myStopsList = response});
  }

}
