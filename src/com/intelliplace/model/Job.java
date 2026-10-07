package com.intelliplace.model;

public class Job {

    private int jobId;
    private int companyId;
    private String jobRole;
    private double packageLpa;
    private double minCgpa;
    private String requiredSkills;

    public Job() {
    }

    public Job(
            int jobId,
            int companyId,
            String jobRole,
            double packageLpa,
            double minCgpa,
            String requiredSkills) {

        this.jobId = jobId;
        this.companyId = companyId;
        this.jobRole = jobRole;
        this.packageLpa = packageLpa;
        this.minCgpa = minCgpa;
        this.requiredSkills = requiredSkills;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public double getPackageLpa() {
        return packageLpa;
    }

    public void setPackageLpa(double packageLpa) {
        this.packageLpa = packageLpa;
    }

    public double getMinCgpa() {
        return minCgpa;
    }

    public void setMinCgpa(double minCgpa) {
        this.minCgpa = minCgpa;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
}