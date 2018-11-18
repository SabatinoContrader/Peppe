import { Component, OnInit, ChangeDetectorRef } from "@angular/core";
import { Router } from '@angular/router';
import { StopService } from "../../../../_services/stop.service";
import { PaymentService } from "../../../../_services/payment.service";
import { Stop } from "../../../../_models/Stop";

@Component({
    selector: "app-stops-in-progress",
    templateUrl: "./stops-in-progress.component.html",
    styleUrls: ["./stops-in-progress.component.scss"]
})


export class StopsInProgressComponent implements OnInit {

    wallet: any;
    myStopsList: Stop[];

    constructor(private stopService: StopService, private router: Router, private paymentService: PaymentService, private ref: ChangeDetectorRef) {

    }

    ngOnInit() {
        this.stopService.myStopsList().subscribe(response => { this.myStopsList = response });
        this.paymentService.getwallet().subscribe(response => { this.wallet = response });
    }

    updateWallet(newAmount: number) {
        this.wallet.amount = newAmount;
        this.ref.detectChanges();
    }

}

