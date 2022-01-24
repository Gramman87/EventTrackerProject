import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Services } from '../models/services';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  private baseUrl = environment.baseUrl;
  private url = this.baseUrl +'api/services';

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

  index(): Observable<Services[]> {
    return this.http.get<Services[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            "ServicesService.index(): error retrieving Services list: " + err
          )
        );
      })
    );
  }

  show(id: number): Observable<Services> {
    return this.http.get<Services>(this.url + "/" + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            "ServicesService.show(): error retrieving Service: " + err
          )
        );
      })
    );
  }

  create(service: Services): Observable<Services[]> {
    return this.http.post<Services[]>(this.url, service, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error(
            "ServicesService.create(): error creating Service: " + err
          )
        );
      })
    );
  }

  update(service: Services) {
    return this.http.put<Services>(this.url + "/" + service.id, service, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error("ServicesService.update(): error updating Service:");
        console.error(err);
        return throwError(
          () => new Error(
            "ServicesService.update(): error updating Service"
          )
        );
      })
    )
  }

  destroy(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.error("ServicesService.destroy(): error deleting Service:");
        console.error(err);
        return throwError(
          () => new Error(
            "ServicesService.update(): error deleting Services"
          )
        );
      })
    );
  }
}
