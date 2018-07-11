import { Component, OnInit } from '@angular/core';
import { Report } from '../../models/Report';
import { ReportService } from '../../services/report.service';
import { Router } from '../../../node_modules/@angular/router';
import { User } from '../../models/User';

@Component({
  selector: 'app-report-near',
  templateUrl: './report-near.component.html',
  styleUrls: ['./report-near.component.css']
})
export class ReportNearComponent implements OnInit {

  map: Map<string, string> = new Map<string, string>();

  reports: Array<Report>;

  constructor(private reportService: ReportService, private router: Router) { }

  ngOnInit() {
    this.map.set('0', "Avviso del gestore");
    this.map.set('1', "Abuso spazio dedicato a persone con disabilità");
    this.map.set('2', "Disservizio stradale");
    this.map.set('3', "Problema riscontrato nell'usufruire del servizio");

    this.onOpenReportNear();
  }

  onOpenReportNear(): void{

    console.log("QUI");
    //use localization to get position
    this.reportService.onOpenNear("changewithlat","changewithlng").subscribe((response) => {
        console.log(response);
        this.reports = response;

    });}

  back(): void
  {
    var user: User = JSON.parse(sessionStorage.getItem("user"));
    if(user.type == 0) this.router.navigateByUrl("/homeOwner");
    if(user.type == 1) this.router.navigateByUrl("/homeDriver");
  }
}
