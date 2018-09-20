import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import { Observable, Subject } from "rxjs";
import "rxjs/add/operator/map";

import { TokenStorage } from "./token-storage.service";
import { UtilsService } from "../../_services/utils.service";
import { AccessData } from "../_models/access-data";

@Injectable()
export class AuthenticationService {

    public onCredentialUpdated$: Subject<AccessData>;

    constructor(private http: Http,
                private tokenStorage: TokenStorage,
                private utils: UtilsService) {
    }

    /**
	 * Get user roles
	 * @returns {Observable<any>}
	 */
	public getUserRoles(): Observable<any> {
		return this.tokenStorage.getUserRoles();
	}

    login(email: string, password: string) {
        return this.http.post('/api/authenticate', JSON.stringify({ email: email, password: password }))
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let user = response.json();
                if (user && user.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}