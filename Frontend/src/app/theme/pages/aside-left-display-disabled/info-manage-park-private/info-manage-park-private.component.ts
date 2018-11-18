import { Component, OnInit } from '@angular/core';
import { SlotService } from '../../../../_services/slot.service';
import { NgForm } from '@angular/forms';
import { Slot } from '../../../../_models/Slot';

@Component({
    selector: 'app-info-manage-park-private',
    templateUrl: './info-manage-park-private.component.html',
    styleUrls: ['./info-manage-park-private.component.scss']
})

export class InfoManageParkPrivateComponent implements OnInit {

    public slots: Array<Slot>;
    public selectedSlotID: number;
    public selectedSlot: Slot;
    public exists: boolean = false;
    public dummyCopySlot: Slot;

    constructor(private slotService: SlotService) { }

    ngOnInit() {
        this.slotService.getSlots().subscribe((response) => {
            this.slots = response;
        });
    }

    selectSlot(slot: Slot): void {
        this.exists = true;

        this.selectedSlot = slot;
        this.dummyCopySlot = Object.assign({}, slot);

        this.selectedSlotID = slot.id;

    }
}