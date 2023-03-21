package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  ConcurrentMap<Long, Post> posts = new ConcurrentHashMap<>();

  AtomicLong idPost = new AtomicLong(0);

  public List<Post> all() {

    return new ArrayList<>(posts.values());
  }

  public Optional<Post> getById(long id) {

    return posts.containsKey(id) ? Optional.of(posts.get(id)) : Optional.empty();
  }

  public Post save(Post post) {

    long id = post.getId();
    if (id == 0) {
      id = idPost.incrementAndGet();
      post.setId(id);
      posts.put(id, post);
    }
    else {
      if (posts.containsKey(id)) {
        posts.put(id, post);
      }
      else {
        throw new NotFoundException("Post with id " + id + " not found: can't update post");
      }
    }
    return post;
  }

  public void removeById(long id) {
    if (posts.containsKey(id)) {
      posts.remove(id);
    }
    else {
      throw new NotFoundException("Post with id " + id + " not found: can't delete post");
    }
  }

}
