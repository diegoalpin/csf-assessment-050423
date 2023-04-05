import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { Review } from './models';

@Injectable()
export class MovieService {
  constructor(private http: HttpClient) {}
  // URL: string = 'http://localhost:8080/api';
  URL: string = '/api';

  public getReviews(moviename: string): Promise<Review[]> {
    const params = new HttpParams().append('query', moviename);
    const searchURL = this.URL + '/search';
    return firstValueFrom(this.http.get<Review[]>(searchURL, { params }));
  }

  public postComment(comment: Comment): Promise<Comment>{
    const postURL =this.URL+ '/comment';
    return firstValueFrom( this.http.post<Comment>(postURL, comment))
    
  }
}
