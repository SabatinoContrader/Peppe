import { NO_ERRORS_SCHEMA } from "@angular/core";
import { BalanceComponent } from "./balance.component";
import { ComponentFixture, TestBed } from "@angular/core/testing";

describe("BalanceComponent", () => {

    let fixture: ComponentFixture<BalanceComponent>;
    let component: BalanceComponent;
    beforeEach(() => {
        TestBed.configureTestingModule({
            schemas: [NO_ERRORS_SCHEMA],
            providers: [
            ],
            declarations: [BalanceComponent]
        });

        fixture = TestBed.createComponent(BalanceComponent);
        component = fixture.componentInstance;

    });

    it("should be able to create component instance", () => {
        expect(component).toBeDefined();
    });

});
