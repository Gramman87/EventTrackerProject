import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Services } from '../models/services';

@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  private baseUrl = 'http://localhost:8080/';
  private url = this.baseUrl +'api/services';

  constructor(
    private http: HttpClient,
    private date: DatePipe
  ) { }

  index(): Observable<Services[]> {
    return this.http.get<Services[]>(this.url).pipe(
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
    return this.http.get<Services>(this.url + "/" + id).pipe(
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
    return this.http.post<Services[]>(this.url, service).pipe(
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
    return this.http.put<Services>(this.url + "/" + service.id, service).pipe(
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
    return this.http.delete<void>(`${this.url}/${id}`).pipe(
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
