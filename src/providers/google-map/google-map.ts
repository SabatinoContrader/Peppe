import { ManagementCarplace } from './../../models/ManagementCarplace';
import { Slot } from './../../models/Slot';
import { Car } from './../../models/Car';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';
import { User } from '../../models/User';

/*
  Generated class for the GoogleMapProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class GoogleMapProvider {

  constructor(private http: HttpClient) {
    
  }

  

  getNotStopCarList(): Observable<Array<Car>> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    return this.http.get<Array<Car>>('http://localhost:58708/api/getCarWithoutStop?username=' + user.username)
    .pipe();
  }

  getNearSlots(lat: number,lng: number,idCar:number): Observable <Array<Slot>> {
    return this.http.get<Array<Slot>>('http://localhost:58708/api/updateParkings?lat='+lat+'&lng='+lng+'&id_car='+idCar)
    .pipe();
  }

  getUpdatedSlots(): Observable <Array<ManagementCarplace>> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    return this.http.get<Array<ManagementCarplace>>('http://localhost:58708/api/updateSlots?username=' + user.username)
    .pipe();
  }

}
