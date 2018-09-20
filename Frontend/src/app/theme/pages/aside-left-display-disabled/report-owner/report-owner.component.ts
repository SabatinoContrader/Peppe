import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ReportService } from '../../../../_services/report.service';

@Component({
  selector: 'app-report-owner',
  templateUrl: './report-owner.component.html',
  styleUrls: ['./report-owner.component.scss']
})
export class ReportOwnerComponent implements OnInit {

  constructor(private reportService: ReportService, private router: Router) { }

  ngOnInit() {
  }

  sendReport(f: NgForm): void {
    // 0 is type of owner report
    //lat e lng saranno da gestire diversamente...
    this.reportService.sendReport(f.value.description, "0", 41.1346778, 14.780854199999999,null).subscribe((response) => {
        console.log("RISPOSTA: " + response);
        this.router.navigateByUrl("/managementPark");
    });
  }

}
