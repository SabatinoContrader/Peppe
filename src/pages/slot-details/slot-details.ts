import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ViewController } from 'ionic-angular';
import { ManagementCarplace } from '../../models/ManagementCarplace';

/**
 * Generated class for the SlotDetailsPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-slot-details',
  templateUrl: 'slot-details.html',
})
export class SlotDetailsPage {
  obj: ManagementCarplace;
  list: any;

  constructor(public navCtrl: NavController, public navParams: NavParams, private viewCtrl: ViewController) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad SlotDetailsPage');
    this.obj = this.navParams.data;
    console.log(this.obj);
    this.list = this.obj.stop_list;

            // var exceedingNumber = (obj.slot.number_carplace - obj.slot.number_carplace_free) - obj.stop_list.length;
        // if (exceedingNumber > 0) {
        //   self.exceedingInStot = "Ci sono " + exceedingNumber + " parcheggi non registrati in questa area parcheggi";         
        // }
        // //a quanto pare siamo fuori da ngZone e quindi dobbiamo dirgli manualmente di leggere i cambiamenti
        // self.ref.detectChanges();
  }

  backToHome(){
    this.viewCtrl.dismiss();
  }

}
