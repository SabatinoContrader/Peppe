import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthNotice } from '../_interfaces/auth-notice.interface';

@Injectable()
export class AuthNoticeService {
    onNoticeChanged$: BehaviorSubject<AuthNotice>;

    constructor() {
        this.onNoticeChanged$ = new BehaviorSubject(null);
    }

    setNotice(message: string, type?: string) {
        const notice: AuthNotice = {
            message: message,
            type: type
        };
        this.onNoticeChanged$.next(notice);
    }
}
