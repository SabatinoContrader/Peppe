import { Component, OnInit, ChangeDetectorRef } from "@angular/core";
import { Router } from '@angular/router';
import { StopService } from "../../../../_services/stop.service";
import { PaymentService } from "../../../../_services/payment.service";
import { Stop } from "../../../../_models/Stop";

@Component({
    selector: "app-stops-history",
    templateUrl: "./stops-history.component.html",
    styleUrls: ["./stops-history.component.scss"]
})

export class StopsHistoryComponent implements OnInit {

  
    myStopsList: Stop[];

    constructor(private stopService: StopService, private router: Router, private paymentService: PaymentService, private ref: ChangeDetectorRef) {

    }

    ngOnInit() {
        this.stopService.stopsHistory().subscribe(response => { this.myStopsList = response });
     }


}


