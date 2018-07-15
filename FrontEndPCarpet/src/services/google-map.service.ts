import { Injectable } from "@angular/core";
import { HttpClient } from "../../node_modules/@angular/common/http";
import { Observable , of} from "../../node_modules/rxjs";
import { tap, catchError } from "../../node_modules/rxjs/operators";
import { Slot } from "../models/Slot";
import { ManagementCarplace } from "../models/ManagementCarPlace";
import { User } from "../models/User";
import { Car } from "../models/Car";

/**
 * @description
 * @class
 */
@Injectable()
export class GoogleMapService {

  constructor(private http: HttpClient) {
    
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log('${operation} failed: ${error.message}');
      return of(result as T);
    };
  }

  getNotStopCarList(): Observable<Array<Car>> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    return this.http.get<Array<Car>>('http://localhost:58708/api/getCarWithoutStop?username=' + user.username)
    .pipe(tap((response) => console.log("getNotStopCarList"), catchError(this.handleError("report error", {})))
  );
  }

  getNearSlots(lat: number,lng: number): Observable <Array<Slot>> {
    return this.http.get<Array<Slot>>('http://localhost:58708/api/updateParkings?lat='+lat+'&lng='+lng)
    .pipe(tap((response) => console.log("success getting slots " + response), catchError(this.handleError("report error", {})))
  );}

  getUpdatedSlots(): Observable <Array<ManagementCarplace>> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    return this.http.get<Array<ManagementCarplace>>('http://localhost:58708/api/updateSlots?username=' + user.username)
    .pipe(tap((response) => console.log("success getting owner slots " + response), catchError(this.handleError("report error", {})))
  );}
}
