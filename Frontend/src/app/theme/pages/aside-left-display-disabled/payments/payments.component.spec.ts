import { NO_ERRORS_SCHEMA } from "@angular/core";
import { PaymentsComponent } from "./payments.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("PaymentsComponent", () => {

    let fixture: ComponentFixture<PaymentsComponent>;
    let component: PaymentsComponent;
    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            providers: [
            ],
            declarations: [PaymentsComponent]
        });

        fixture = TestBed.createComponent(PaymentsComponent);
        component = fixture.componentInstance;

    });

    it("should be able to create component instance", () => {
        expect(component).toBeDefined();
    });

});
