import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovieReviewListComponent } from './components/movie-review-list/movie-review-list.component';
import { PostCommentComponent } from './components/post-comment/post-comment.component';
import { SearchReviewComponent } from './components/search-review/search-review.component';

const routes: Routes = [
  { path: '', component: SearchReviewComponent },
  { path: 'view1/:moviename', component: MovieReviewListComponent },
  { path: 'view2/:title', component: PostCommentComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' },
  // { path: 'customer/:custId', component: CustomerComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
