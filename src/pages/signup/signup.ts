import { LoginPage } from './../login/login';
import { UserProvider } from './../../providers/user/user';
import { User } from './../../models/User';
import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { NgForm } from '../../../node_modules/@angular/forms';

/**
 * Generated class for the SignupPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-signup',
  templateUrl: 'signup.html',
})
export class SignupPage {
  feedback: string;

  constructor(public navCtrl: NavController, public navParams: NavParams, private userProvider: UserProvider) {
  }

  ionViewWillEnter() {
    console.log('ionViewDidLoad SignupPage');
  }

  signup(f: NgForm): void{
    var user = new User(f.value.username, f.value.password, 1, f.value.name, f.value.surname, "", -1, "", f.value.phone, f.value.email);
    this.userProvider.signup(user).subscribe((response) => {
      if(response)
        this.feedback = "Registrazione effettuata con successo";
      else
        this.feedback = "Registrazione non andata a buon fine";
    this.userProvider.changeFeedback(this.feedback);
    this.navCtrl.push(LoginPage);
    });
  }

}
