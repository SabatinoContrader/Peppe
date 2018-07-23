import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

/**
 * Generated class for the UsefullNumbersPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-usefull-numbers',
  templateUrl: 'usefull-numbers.html',
})
export class UsefullNumbersPage {

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewWillEnter() {
    console.log('ionViewDidLoad UsefullNumbersPage');
  }

}
