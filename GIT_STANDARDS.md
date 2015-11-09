# Git Standards

The following contains the rules for how our team will use git throughout this project.

All additions to master will be done via branches and pull requests.

When adding code, create a new branch.  Each new branch will be named with one of the following prefixes:

* feature_(name): to be used when creating a new feature
* update_(name): to be used when updating or changing an existing feature
* refactor_(name): to be used when refactoring some code

"name" should describe the code you are committing

In terms of commit messages, you message should at least have a verb and object describing the commit.

Once you commit all code to a branch and want to merge with master, create a pull request.  Have a partner check your
pull request.  The partner can either merge the branch to give a LGTM (looks good to me) for you to merge the branch.

How to assess a pull request:
1. Checkout the branch requesting to be merged.
2. Run the code in the branch.
3. Each subgroup should have its own method of code evaluation.  J-Unit tests are a good option.

### Example

For example, suppose we wanted to add, update, and refactor a minimap to our project:

feature: initial creation of minimap

1. create a feature_minimap branch 
2. code up initial minimap classes
3. commit code to branch; a plausible commit message is "Created initial classes for minimap."
4. do a pull request to merge this branch onto master
5. have a partner check your code; they can either merge the branch or give you an LGTM for you to merge
6. feature_minimap is now merged with master

update: updating minimap by making it observable (assuming feature_minimap has already been created)

1. create an update_minimap_observable branch
2. update the appropriate classes to make the minimap observable
3. commit code to branch; a plausible commit message is "Updated minimap to be observable."
4. do a pull request to merge this branch onto master
5. have a partner check your code; they can either merge the branch or give you an LGTM for you to merge
6. update_minimap_observable is now merged with master

refactor: refactor minimap to cut down on repeated code

1. create an refactor_minimap_repetition branch
2. refactor minimap classes
3. commit code to branch; a plausible commit message is "Refactored minimap to cut down repetition."
4. do a pull request to merge this branch onto master
5. have a partner check your code; they can either merge the branch or give you an LGTM for you to merge
6. refactor_minimap_repetition is now merged with master

### Responsibilities

In order for these standards to work, you MUST WORK TOGETHER WITH YOUR PARTNER!!!  I know we all have our individual
coding responsibilities.  However, in order for the master branch to progress and people to move on with their work, 
we must have frequent pull requests, fixes (if needed), and merges.  So, if your partner does a pull request, checking 
that pull request should be a top priority! 

Only under specfial circumstances are you allowed to push directly to master.  Under those circumstances, you will need
permission from the majority of the group to submit to master.

The only changes which may be pushed to master are small documentation changes (i.e. adding netid, links, etc., to README).  

When in doubt, always do a pull request.

