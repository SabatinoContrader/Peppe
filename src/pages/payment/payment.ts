import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { PaymentProvider } from '../../providers/payment/payment';
import { Payment } from '../../models/Payment';
import { AllPayment } from '../../models/AllPayment';

/**
 * Generated class for the PaymentPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-payment',
  templateUrl: 'payment.html',
})
export class PaymentPage {

  paymentList: Payment[];

  constructor(private paymentProvider: PaymentProvider, public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewWillEnter() {
    console.log('ionViewDidLoad PaymentPage');
    this.paymentProvider.getpayments().subscribe(response =>  this.paymentList = (response as AllPayment).payment_list   );
  }

}
