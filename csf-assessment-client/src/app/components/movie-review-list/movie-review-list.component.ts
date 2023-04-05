import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from 'src/app/models';
import { MovieService } from 'src/app/movie.service';

@Component({
  selector: 'app-movie-review-list',
  templateUrl: './movie-review-list.component.html',
  styleUrls: ['./movie-review-list.component.css'],
})
export class MovieReviewListComponent implements OnInit {
  moviename!: string;
  reviews!: Review[];
  notempty:boolean = true
  notavailableimage = 'https://diego15tfip.sgp1.digitaloceanspaces.com/myObject%2Fplaceholder.jpg'

  constructor(
    private activatedRoute: ActivatedRoute,
    private movieSvc: MovieService,
    private router:Router
  ) {}

  ngOnInit(): void {
    this.moviename = this.activatedRoute.snapshot.params['moviename'];
    // throw new Error('Method not implemented.');
    this.movieSvc
      .getReviews(this.moviename)
      .then((result:Review[]) => {
        this.reviews = result
        if(result.length==0){
          this.notempty = false
        }
        else{
          this.notempty = true
        }
      });
  }
  
  setLastSearch(){
    localStorage.setItem('lastSearch',this.moviename)
    
  }
}
