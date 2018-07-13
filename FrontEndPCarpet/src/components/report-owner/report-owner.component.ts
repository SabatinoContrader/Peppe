import { Component, OnInit } from '@angular/core';
import { ReportService } from '../../services/report.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-report-owner',
  templateUrl: './report-owner.component.html',
  styleUrls: ['./report-owner.component.css']
})
export class ReportOwnerComponent implements OnInit {

  constructor(private reportService: ReportService, private router: Router) { }

  ngOnInit() {
  }

  sendReport(f: NgForm): void {
    // 0 is type of owner report
    this.reportService.sendReport(f.value.description, "0").subscribe((response) => {
        console.log("RISPOSTA: " + response);
        this.router.navigateByUrl("/homeOwner");
    });
  }

}
