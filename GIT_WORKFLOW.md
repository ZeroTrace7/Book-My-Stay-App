# Git Workflow Guide

This project follows a clean `main` + `develop` + `feature/*` workflow. Use the steps below to configure Git and keep the history organized.

## Step 2: Git User Configuration

Check current user:

```bash
git config user.name
git config user.email
```

If the user is different, reset globally:

```bash
git config --global user.name "ZeroTrace7"
git config --global user.email "shreyashgupta999@gmail.com"
```

## Step 3: Git Repository Configuration (Local)

If you cloned the repo already, you can skip `git init` and `git remote add`.

```bash
git init
git branch -M main
git remote add origin https://github.com/ZeroTrace7/Book-My-Stay-App.git
git remote -v
```

Note: This repo uses `https://github.com/ZeroTrace7/Book-My-Stay-App.git`.

Expected:

- Repo connected

## Step 4: Initial Commit

```bash
git add .
git commit -m "Base application setup"
git push origin main
```

Note: Ensure that `main` is pushed to the remote repository.

Verify branch:

```bash
git branch
```

Expected:

- `main` appears

## Step 5: Git Branching Strategy

### 5.1 Create Develop Branch

```bash
git checkout -b develop
git push -u origin develop
```

Always make sure you are on `develop` before creating a feature branch.

Verify branch:

```bash
git branch
```

### 5.2 Create Feature Branch (Example: UC1)

```bash
git checkout -b feature/UC1
```

Work on the use case, then commit and push:

```bash
git add .
git commit -m "UC1: Add welcome message module"
git push origin feature/UC1
```

Merge to develop:

```bash
git checkout develop
git pull
git merge feature/UC1
git push
```

## UC2 Example

```bash
git checkout develop
git checkout -b feature/UC2
```

Work on the use case, then commit and push:

```bash
git add .
git commit -m "UC2: Hardcoded palindrome"
git push origin feature/UC2
```

Merge to develop:

```bash
git checkout develop
git pull
git merge feature/UC2
git push
```

