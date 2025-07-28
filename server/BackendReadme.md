<style>
    h3{
        color: red;
        font-weight: bold;
        border-bottom: 1px solid black;
        padding-bottom: 5px;
    }
</style>
### N + 1 Select Problem in Fetching all blogs

#### What actually happens here is
- Main Blog Fetch Query to fetch all blogs
- Additional Queries for Category and User 
    - These are triggered for every single blog entry - meaning if i have 10 blogs and each one has a Category and User, i'll get :- 
    - **10 Queries for categories**
    - **10 Queries for users**
    - This is because of **lazy Loading** in my entity relationships
- Total - `1 + 2N` queries which is inefficient.

#### Solution :-
- Use `@EntityGraph` or `Join Fetch`

#### Option 1:
```java
@EntityGraph(attributePaths = {"user", "category"})
List<Blog> findAll();
```
This tells Hibernate: “When you fetch blogs, also fetch the associated user and category in the same query.” - 1 single Query using JOINS.

#### Option 2:
```java
@Query("SELECT b FROM Blog b JOIN FETCH b.user JOIN FETCH b.category")
List<Blog> findAllWithUserAndCategory();

```