import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { tap, catchError } from "rxjs/operators";
import { Stop } from "../_models/Stop";
import { of } from "rxjs/observable/of";


/*
 * @description
 * @class
 */
@Injectable()
export class StopService {

    constructor(private http: HttpClient) { }

    private handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error);
            console.log('${operation} failed: ${error.message}');
            return of(result as T);
        };
    }


    //user corrente da backend?
    myStopsList(): Observable<any> {
        return this.http.get<any>('http://localhost:58708/api/myStopsList')
            .pipe(tap((response) => console.log(response), catchError(this.handleError("extension error", {}))));
    }

    stopsHistory(): Observable<any> {
        return this.http.get<any>('http://localhost:58708/api/stopsHistory')
            .pipe(tap((response) => console.log(response), catchError(this.handleError("extension error", {}))));
    }


    prolungaSosta(minute: number, stop: Stop): any {
        // var newFinish= new Date((new Date(stop.finish)).getTime() + (60*1000*minute));
        // console.log(newFinish.toString());
        // var newStop: Stop = new Stop(stop.id_stop, stop.address, stop.start, newFinish, stop.name, stop.price);
        return this.http.put<any>('http://localhost:58708/api/extensionstops/' + stop.id_stop, JSON.parse(minute.toString()))
            .pipe(tap((response) => console.log(response), catchError(this.handleError("extension error", {}))));
    }

}
