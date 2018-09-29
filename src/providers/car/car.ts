import { Car } from './../../models/Car';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs/Observable';
import { User } from '../../models/User';
import { SERVER_API_URL } from '../../constants/constants';


/*
  Generated class for the CarProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class CarProvider {
  feedback: string;

  constructor(public http: HttpClient) {
    console.log('Hello CarProvider Provider');
  }

  myCarsList(): Observable<any>{
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    return this.http.get<any>(SERVER_API_URL + '/api/myCarsList?username='+user.username)
    .pipe();
  }

  deleteCar(id: number): Observable<any>{
    return this.http.delete<boolean>(SERVER_API_URL + '/api/removeCar?id='+id)
    .pipe();
  }

  addNewCar(license_plate: string, name: string): Observable<Car>{
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    var car = new Car(0, license_plate, name, user.username);
    return this.http.post<Car>(SERVER_API_URL + '/api/addCar', car)
    .pipe();
  }

  changeFeedback(message: string){
    this.feedback = message;
  }

  deleteFeedback(){
    this.feedback = "";
  }
}
