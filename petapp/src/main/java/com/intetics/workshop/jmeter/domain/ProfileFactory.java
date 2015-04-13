package com.intetics.workshop.jmeter.domain;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Random;

// todo: add content generator
// todo: add location generator
@Component
public class ProfileFactory {

    private static final Random RANDOM = new Random();
    private static final int MAX_ID = 10000;
    private static final List<String> profileTitles = Lists.newArrayList(
            "Lawyer",
            "Public Administration Director",
            "Oil & Gas Drilling Supervisor",
            "Primary Production Manager",
            "Health Care Manager",
            "Human Resources Manager",
            "Head Nurse & Medical Supervisor",
            "Social",
            "Financial Administrator",
            "Electrical & Telecommunications Contractor",
            "Specialized Engineer ",
            "Audiologists & Speech",
            "Probation & Parole Officer",
            "Government Program Officer",
            "Post",
            "Registered Nurse",
            "School Principal & Administator",
            "Psychologist",
            "Longshore Workers",
            "Scientific Research Manager",
            "Senior Business Manager",
            "Education Policy Resarchers",
            "Real Estate & Financial Manager",
            "Engineering Inspector",
            "Civil Engineer",
            "Software Engineer",
            "Computer Systems Manager",
            "Heavy Construction Equipment Supervisor",
            "Banking & Credit Manager",
            "Business Administrative Officer",
            "Occupational Therapist",
            "Nondestructive Tester ",
            "Utilities Manager",
            "Engineering Manager",
            "Account Executive & Consultant",
            "Secondary School Teacher",
            "Pilot",
            "Sales & Marketing Manager",
            "College Instructor",
            "Pharmacist",
            "Health & Occupation Inspector",
            "Petroleum Engineer",
            "Economic Development Officer",
            "Geologist",
            "Senior Government Manager",
            "Business Services Manager",
            "Power Systems Operator",
            "Dental Hygienist",
            "Oil & Gas Well Operator",
            "School & Guidance Counsellor",
            "Medical Radiation Technologist",
            "Chemical Engineer",
            "Correctional Services Officers",
            "Human Resources Specialist",
            "University Professor",
            "Urban Planner",
            "Mining Supervisor",
            "Fire",
            "Computer Engineer",
            "Avionics Mechanics",
            "Petroleum",
            "Auto Worker",
            "Mechanical Engineer",
            "Aerospace Engineer",
            "Construction Manager",
            "Statistician & Actuaries",
            "Oil & Gas Services",
            "Locomotive Engineer",
            "Elementary School & Kindergarten Teacher",
            "Financial Analyst",
            "Architect",
            "Dietitian & Nutritionist",
            "Telecommunication Manager",
            "Health Policy Researcher",
            "Police Officer",
            "Ambulance Attendant",
            "Personal Financial Planner",
            "Construction Trades",
            "Industrial Technician",
            "Electronics engineer",
            "Manufacturing Manager",
            "Mapping Technologist",
            "Natural & Applied Science Researcher",
            "Transportation Manager",
            "Pipefitting Contractor & Supervisor",
            "Mechanical Engineering Technician",
            "Medical Lab Technician",
            "Media Manager",
            "Economic Analyst ",
            "Biologist",
            "Financial Auditor & Accountant",
            "Social Worker",
            "Power Line & Cable Worker",
            "Information Systems Analyst",
            "Land Surveyor",
            "Purchasing Manager",
            "Physiotherapist",
            "Industrial Electrician",
            "Economist & Policy Researcher",
            "Industrial & Manufacturing Engineer"
    );

    public Profile newProfile() {
        Collections.shuffle(profileTitles);
        return new Profile(
                Long.valueOf(RANDOM.nextInt(MAX_ID)),
                profileTitles.get(0),
                "B",
                "C");
    }
}
