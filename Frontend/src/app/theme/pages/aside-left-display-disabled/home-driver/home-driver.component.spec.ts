import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeDriverComponent } from './home-driver.component';

describe('HomeDriverComponent', () => {
    let component: HomeDriverComponent;
    let fixture: ComponentFixture<HomeDriverComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [HomeDriverComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(HomeDriverComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
