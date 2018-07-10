import { Component, OnInit } from '@angular/core';
import { NgForm } from '../../../node_modules/@angular/forms';
import { Router } from '../../../node_modules/@angular/router';
import { ReportService } from '../../services/report.service';

@Component({
  selector: 'app-report-driver',
  templateUrl: './report-driver.component.html',
  styleUrls: ['./report-driver.component.css']
})
export class ReportDriverComponent implements OnInit {

  constructor(private reportService: ReportService, private router: Router) { }

  ngOnInit() {
  }

  sendReport(f: NgForm): void {
    console.log("reportType:" + f.value.select);
    this.reportService.sendReport(f.value.description, f.value.select.value).subscribe((response) => {
        console.log("RISPOSTA: " + response);
        this.router.navigateByUrl("/homeDriver");
    });
  }
}
