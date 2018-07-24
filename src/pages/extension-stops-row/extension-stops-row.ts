import { Component, Input } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Stop } from '../../models/Stop';
import { NgForm } from '../../../node_modules/@angular/forms';
import { StopProvider } from '../../providers/stop/stop';

/**
 * Generated class for the ExtensionStopsRowPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'tr[page-extension-stops-row]',
  templateUrl: 'extension-stops-row.html',
})
export class ExtensionStopsRowPage {
  @Input() stop: Stop;
  price: number;

  constructor(public navCtrl: NavController, public navParams: NavParams, private stopProvider: StopProvider) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ExtensionStopsRowPage');
  }

  detectChange(f:NgForm): void{
    console.log("time: "+ f.value.select);
    this.price = (this.stop.price / 60) * f.value.select; 
}

prolungaSosta(f: NgForm): void{
  this.stopProvider.prolungaSosta(f.value.select, this.stop).subscribe((response) => {
    this.stop.finish = response;
  });
}


}
