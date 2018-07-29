import { Stop } from './../../models/Stop';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
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

  constructor(public navCtrl: NavController, public navParams: NavParams, private stopProvider: StopProvider, private alertCtrl: AlertController) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ExtensionStopsPage');
    this.stopProvider.myStopsList().subscribe(response => {
      this.myStopsList = response
    });
  }

  prolungaSosta(i): void {


    let alert = this.alertCtrl.create({
      enableBackdropDismiss: false,
    });

    alert.setTitle("<b>Per quanti minuti vuoi prolungare la sosta?</b>");

    alert.addInput({
      type: 'radio',
      label: '15 min ' + '(' + (this.myStopsList[i].price / 60) * 15 + "\u20AC" + ')',
      value: '15',
      checked: true
    });

    alert.addInput({
      type: 'radio',
      label: '30 min ' + '(' + (this.myStopsList[i].price / 60) * 30 + "\u20AC" + ')',
      value: '30'
    });

    alert.addInput({
      type: 'radio',
      label: '45 min ' + '(' + (this.myStopsList[i].price / 60) * 45 + "\u20AC" + ')',
      value: '45'
    });

    alert.addInput({
      type: 'radio',
      label: '1 h ' + '(' + (this.myStopsList[i].price / 60) * 60 + "\u20AC" + ')',
      value: '60'
    });

    alert.addInput({
      type: 'radio',
      label: '1 h 15 min ' + '(' + (this.myStopsList[i].price / 60) * 75 + "\u20AC" + ')',
      value: '75'
    });

    alert.addInput({
      type: 'radio',
      label: '1 h 30 min ' + '(' + (this.myStopsList[i].price / 60) * 90 + "\u20AC" + ')',
      value: '90'
    });

    alert.addInput({
      type: 'radio',
      label: '1 h 45 min ' + '(' + (this.myStopsList[i].price / 60) * 105 + "\u20AC" + ')',
      value: '105'
    });

    alert.addInput({
      type: 'radio',
      label: '2 h ' + '(' + (this.myStopsList[i].price / 60) * 120 + "\u20AC" + ')',
      value: '120'
    });

    alert.addButton({
      text: "OK",
      handler: data => {
        this.stopProvider.prolungaSosta(data, this.myStopsList[i]).subscribe((response) => {
          var updateStop = Object.assign({}, this.myStopsList[i]);
          updateStop.finish = response;
          this.myStopsList[i] = updateStop;
        });
      }
    });
    alert.addButton("ANNULLA");

    alert.present();

  };

}
