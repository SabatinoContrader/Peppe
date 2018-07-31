import { UserProvider } from './../../providers/user/user';
import { Component} from '@angular/core';
import { NavController, ModalController, MenuController, ToastController } from 'ionic-angular';
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
  //user: User;
  rootPage = "SignupPage";

  constructor(public navCtrl: NavController, 
    private userProvider: UserProvider, 
    private modalCtrl: ModalController, 
    private menuCtrl: MenuController,
    private toastCtrl: ToastController
  ) {

  }

  ionViewWillEnter() {
    this.menuCtrl.enable(false, "driverMenu");
    this.menuCtrl.enable(false, "ownerMenu");
    this.feedback = this.userProvider.feedback;
    this.userProvider.deleteFeedback();

    if(this.feedback != null){
    const toast = this.toastCtrl.create({
      message: this.feedback,
      duration: 3000
    });
    toast.present();
  }
}

  login(f: NgForm) {
    console.log("pressedLogin");
    this.userProvider.login(f.value.username, f.value.password).subscribe((response) => {
      if (response != null) {
        if (response.type == 1){
          this.menuCtrl.enable(true, "driverMenu");
          this.navCtrl.push("HomeDriverPage");
        }
        else if(response.type == 0){
          this.menuCtrl.enable(true, "ownerMenu");
          this.navCtrl.push("HomeOwnerPage");
        }
      } else{
        this.feedback = "Username o password errati";
        const toast = this.toastCtrl.create({
          message: this.feedback,
          duration: 3000
        });
        toast.present();
      }
    });
  }

  goToSignup() {
    const modal = this.modalCtrl.create("SignupPage");
    modal.present();
  }


}
