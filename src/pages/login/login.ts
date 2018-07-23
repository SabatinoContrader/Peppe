import { UserProvider } from './../../providers/user/user';
import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { User } from '../../models/User';
import { NgForm } from '../../../node_modules/@angular/forms';

/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})

export class LoginPage {
  feedback: string;
  user: User;
  rootPage = "SignupPage";

  constructor(public navCtrl: NavController, private userProvider: UserProvider) {

  }

  ionViewWillEnter() {
    this.feedback = this.userProvider.feedback;
    this.userProvider.deleteFeedback();
  }

  login(f: NgForm){
    console.log("pressedLogin");
    this.userProvider.login(f.value.username, f.value.password).subscribe((response) => {
      if (response != null) {
        this.user = response;
        sessionStorage.setItem("user", JSON.stringify(this.user));
        if(response.type == 1)
          this.navCtrl.push("HomeDriverPage");
        // else if(response.type == 0)
        // this.navCtrl.push("HomeOwnerPage");
      } else
          this.feedback = "Username o password errati";
    });
  }

  goToSignup(){
    this.navCtrl.push("SignupPage");
  }


}
