import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css'],
})
export class SearchReviewComponent {
  form!: FormGroup;
  invalidity: boolean =true

  constructor(private fb: FormBuilder, private router:Router) {}

  ngOnInit(): void {
    this.form = this.createForm();
  }


  searchReviews(){
    const movieName: string = this.form.get('name')?.value
    this.router.navigate(['/view1',movieName])
  }

  checkValidity(){
    const input: string =this.form.get('name')?.value
    // console.info(input)
    if(input.trim().length>=3){
      this.invalidity = false
    }
    else{
      this.invalidity = true
    }

  }
  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
    });
  }
}
