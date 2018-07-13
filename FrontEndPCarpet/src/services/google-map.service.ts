import { Injectable } from "@angular/core";
import { HttpClient } from "../../node_modules/@angular/common/http";
import { Observable , of} from "../../node_modules/rxjs";
import { tap, catchError } from "../../node_modules/rxjs/operators";
import { Slot } from "../models/Slot";

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

  getNearSlots(lat: number,lng: number): Observable <Array<Slot>> {
    return this.http.get<Array<Slot>>('http://localhost:58708/api/updateParkings?lat='+lat+'&lng='+lng)
    .pipe(tap((response) => console.log("success getting slots " + response), catchError(this.handleError("report error", {})))
  );}
}
