import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieService } from 'src/app/movie.service';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css'],
})
export class PostCommentComponent implements OnInit {
  title!: string;
  form!: FormGroup;
  comment!: Comment;
  previousTitle!: string | null;

  constructor(
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private movieSvc: MovieService,
    private router:Router
  ) {}

  ngOnInit(): void {
    this.form = this.createForm();
    this.title = this.activatedRoute.snapshot.params['title'];
    this.previousTitle = localStorage.getItem('lastSearch');
  }

  private createForm(): FormGroup {
    return this.fb.group({
      moviename: this.fb.control<string>(''),
      name: this.fb.control<string>('', [
        Validators.required,
        Validators.minLength(3),
      ]),
      rating: this.fb.control<number>(1, [Validators.required]),
      comment: this.fb.control<string>('', [Validators.required]),
    });
  }

  submitComment() {
    this.comment = this.form.value as Comment;
    console.info(this.comment);
    this.movieSvc
      .postComment(this.comment)
      .then((result) => {
        this.form = this.createForm();
        console.info('Success! callback result is ', result);
        this.navigateBack();
      })
      .catch((error) => {
        console.info(error);
      });
  }

  navigateBack(){
    this.router.navigate(['view1',this.previousTitle]);
  }
}
