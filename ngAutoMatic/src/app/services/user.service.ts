import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl +'api/users';

  constructor(
    private auth: AuthService,
    private http: HttpClient,
    private date: DatePipe
  ) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: "Basic " + this.auth.getCredentials(),
        "X-Requested-With": "XMLHttpRequest"
      }
    }
    return options;
  }

  index(): Observable<User[]> {
    return this.http.get<User[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            "UserService.index(): error retrieving User list: " + err
          )
        );
      })
    );
  }

  show(id: number): Observable<User> {
    return this.http.get<User>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            "UserService.show(): error retrieving User list: " + err
          )
        );
      })
    );
  }

  create(user: User): Observable<User[]> {
    return this.http.post<User[]>(this.url, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            "UserService.create(): error creating User list: " + err
          )
        );
      })
    );
  }

  update(user: User) {
    return this.http.put<User>(this.url + "/" + user.id, user, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error("UserService.destroy(): error updating User:");
        console.error(err);
        return throwError(
          () => new Error(
            "UserService.update(): error updating User"
          )
        );
      })
    )
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error("UserService.destroy(): error deleting user:");
        console.error(err);
        return throwError(
          () => new Error(
            "UserService.destroy(): error deleting User"
          )
        );
      })
    );
  }

}
