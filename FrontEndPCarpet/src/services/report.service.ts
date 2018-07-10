import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';

import { tap, catchError } from '../../node_modules/rxjs/operators';
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
    return this.http.post<number>('http://localhost:58708/api/driverAddReport', report)
    .pipe(tap((response) => console.log("Report"), catchError(this.handleError("report error", {})))
  );
  }
}
