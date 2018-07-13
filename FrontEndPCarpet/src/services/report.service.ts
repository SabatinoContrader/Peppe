import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';

import { tap, catchError } from 'rxjs/operators';
import { Report } from '../models/Report';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      console.log('${operation} failed: ${error.message}');
      return of(result as T);
    };
  }

  sendReport(description: string, type: string): Observable <number> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    var report: Report = new Report(+type,description,"",user.username,0);
    console.log("username: "+ user.username);
    return this.http.post<number>('http://localhost:58708/api/addReport', report)
    .pipe(tap((response) => console.log("Added Report"), catchError(this.handleError("report error", {})))
  );}

  onOpenHystory(): Observable <Array<Report>> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    return this.http.get<Array<Report>>('http://localhost:58708/api/reportHystory?username=' + user.username)
    .pipe(tap((response) => console.log("Retrieved My Report List"), catchError(this.handleError("report error", {})))
  );}

  onOpenNear(lat: string, lng: string): Observable <Array<Report>> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    return this.http.get<Array<Report>>('http://localhost:58708/api/getNearReport?type='+ user.type + '&lat=' + lat + '&lng=' + lng)
    .pipe(tap((response) => console.log("Retrieved Near Report List"), catchError(this.handleError("report error", {})))
  );}
}
