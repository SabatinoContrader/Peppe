import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import { Observable, Subject } from "rxjs";
import "rxjs/add/operator/map";

import { TokenStorage } from "./token-storage.service";
import { UtilsService } from "../../_services/utils.service";
import { AccessData } from "../_models/access-data";

@Injectable()
export class AuthenticationService {
    API_URL = 'api';
	API_ENDPOINT_LOGIN = '/login';
	API_ENDPOINT_REFRESH = '/refresh';
	API_ENDPOINT_REGISTER = '/register';

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

    login(username: string, password: string) {
        // Change this to post and send the credentials in the body
        // Don't send sensitive data in the URL
        return this.http.get(this.API_URL + this.API_ENDPOINT_LOGIN + `?username=${username}&password=${password}`)
            .map((response: Response) => {
                // login successful if there's a jwt token in the response
                let user = response.json();
                // Add '&& user.token' when you have added a JWT to the login
                if (user) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('user', JSON.stringify(user));
                }
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem("user");
        sessionStorage.removeItem("user");
    }
}