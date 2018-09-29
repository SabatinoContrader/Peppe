import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs/Observable';
import { User } from '../../models/User';
import { Report } from '../../models/Report';
import { SERVER_API_URL } from '../../constants/constants';

/*
  Generated class for the ReportProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class ReportProvider {

  constructor(public http: HttpClient) {
    console.log('Hello ReportProvider Provider');
  }

  // passo lat/lng dal report-driver component
  sendReport(description: string, type: string, latitude: number, longitude: number, selectedFile: File): Observable <number> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    //var mediaBytes: byte[];
    
    var report: Report = new Report(+type,description,"19/07/2018",user.username,"",latitude,longitude);

    const fd = new FormData();
    if(selectedFile) fd.append('image',selectedFile,selectedFile.name);
    fd.append('report', JSON.stringify(report));

    console.log("username: "+ user.username);
    console.log("lat al service : "+ report.latitude);
    console.log("long al service : "+ report.longitude);
    return this.http.post<number>(SERVER_API_URL + '/api/addReport', fd)
    .pipe();}

  onOpenHystory(): Observable <Array<Report>> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    return this.http.get<Array<Report>>(SERVER_API_URL + '/api/reportHystory?username=' + user.username)
    .pipe();}

  onOpenNear(lat: number, lng: number): Observable <Array<Report>> {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    console.log("lat al service : "+ lat);
    console.log("long al service : "+ lng);
    return this.http.get<Array<Report>>(SERVER_API_URL + '/api/getNearReport?type='+ user.type + '&lat=' + lat + '&lng=' + lng)
    .pipe();}

}
