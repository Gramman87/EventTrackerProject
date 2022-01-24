import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    const credentials = this.generateBasicAuthCredentials(username, password);
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    return this.http.get(this.baseUrl + "authenticate", httpOptions).pipe(tap((res) => {
      localStorage.setItem("credentials", credentials);
      return res;
    }),
      catchError((err) => {
        console.error(err);
        return throwError(
          () => new Error("AuthService.login(): error loging in user.")
        );
      })
    )
  }

  register(user: User) {
    return this.http.post(this.baseUrl + "register", user).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
          () => new Error("AuthService.register(); error registering user.")
        );
      })
    );
  }

  logout() {
    localStorage.removeItem("credentials");
  }

  checkLogin() {
    if (localStorage.getItem("credentials")) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username: string, password: string) {
    return btoa(`${username}:${password}`);
  }

  getCredentials() {
    return localStorage.getItem("credentials");
  }

}
