import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { Stop } from "../../../../_models/Stop";
import { StopService } from "../../../../_services/stop.service";
import { Router } from "../../../../../../node_modules/@angular/router";
import { NgForm } from "../../../../../../node_modules/@angular/forms";
import { PaymentService } from "../../../../_services/payment.service";

@Component({
  selector: "tr[app-stops-history-row]",
  templateUrl: "./stops-history-row.component.html",
  styleUrls: ["./stops-history-row.component.scss"]
})

export class StopsHistoryRowComponent implements OnInit {
  
  @Input() stop: Stop;
    price: number;
    wallet: any;

    @Output() onWalletChanged: EventEmitter<any> = new EventEmitter<any>();

    constructor(private stopService: StopService, private router: Router, private paymentService: PaymentService) { }

    ngOnInit() {
        this.paymentService.getwallet().subscribe(response => { this.wallet = response });
    }

    detectChange(f: NgForm): void {
        console.log("time: " + f.value.select);
        this.price = (this.stop.price / 60) * f.value.select;
    }


}
