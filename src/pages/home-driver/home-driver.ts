import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import { Car } from '../../models/Car';
import { CarProvider } from '../../providers/car/car';

/**
 * Generated class for the HomeDriverPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-home-driver',
  templateUrl: 'home-driver.html',
})
export class HomeDriverPage {

  private carsList: Array<Car>;
  private selectedCar: Car;

  constructor(public navCtrl: NavController, public navParams: NavParams,private alertCtrl: AlertController,private carProvider: CarProvider) {
    
  }



  ionViewWillEnter() {
    console.log('ionViewDidLoad HomeDriverPage');

    this.carProvider.myCarsList().subscribe(response => {
      this.carsList = response;
      if(this.carsList && this.carsList.length > 0) this.selectedCar = this.carsList[0];
    });
    
  }

  openCar(): void{
    console.log('openCar');
    let alert = this.alertCtrl.create({
      enableBackdropDismiss: false,
    });
    alert.setTitle("<b>Le Mie Auto:</b>");

    this.carsList.forEach(car => {
      
    alert.addInput({
      type: 'radio',
      label: car.name + " (" + car.license_plate +  ")",
      value: car.id+"",
      checked: (car == this.selectedCar)      
      
    });

    });

    alert.addButton({
      text: "OK",
      handler: data => {
        console.log("datachecked: " + data);
        this.selectedCar = this.carsList.find(car => car.id == data);       
        }
      });
    alert.addButton("ANNULLA");
    alert.addButton({
      text: "AGGIUNGI",
      handler: data => {      
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
              this.carsList.push(new Car(0,data.license_plate,data.name,"automobilista1"));
              this.selectedCar = new Car(0,data.license_plate,data.name,"automobilista1");
            }
            );
          }
        });
        alert1.present();
      }
    });
    alert.present();
  }

}
