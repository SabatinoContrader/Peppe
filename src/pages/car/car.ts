import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import { CarProvider } from '../../providers/car/car';
import { Car } from '../../models/Car';

/**
 * Generated class for the CarPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-car',
  templateUrl: 'car.html',
})
export class CarPage {

  carsList: Car[];
  result: string;

  constructor(public navCtrl: NavController, public navParams: NavParams, private carProvider: CarProvider, private alertCtrl: AlertController) {
  }

  ionViewWillEnter() {
    this.result = this.carProvider.feedback;
    this.carProvider.myCarsList().subscribe(response => {this.carsList = response});
    console.log('ionViewDidLoad CarPage');
  }

  deleteCar(id: number){
    this.carProvider.deleteCar(id).subscribe(response => {
      if(response == 0)
        this.result = "Auto in sosta! Devi terminare la sosta prima di poterla eliminare";
      else {
        this.result = "Auto eliminata con successo";
        this.carProvider.myCarsList().subscribe(response => {this.carsList = response});
      }
    });
  }

  addCar(){
    let alert1 = this.alertCtrl.create({
      enableBackdropDismiss: false,
    });
    alert1.setTitle("Aggiungi Auto");
    alert1.addInput({
      name: "license_plate",
      placeholder: "Targa"
    });
    alert1.addInput({
      name: "name",
      placeholder: "Nome"
    });
    alert1.addButton("ANNULLA");
    alert1.addButton({
      text:"AGGIUNGI",
      handler: data => {
        this.carProvider.addNewCar(data.license_plate,data.name).subscribe(response => {
          //La response dovrà essere l'auto creata.
          this.carsList.push(response);
        }
        );
      }
    });
    alert1.present();
  }

}
