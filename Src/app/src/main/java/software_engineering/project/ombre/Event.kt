class Event : AppCompatActivity(), View.OnClickListener {
    private var bizcolor: Int? = null
    private var adcolor: Int? = null
    private lateinit var adventure: TextView
    private lateinit var business: TextView
    private lateinit var adtext: TextView
    private lateinit var biztext: TextView
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_event)

        adventure = findViewById(R.id.adventure)
        business = findViewById(R.id.business)
        viewPager2 = findViewById(R.id.viewPager2)

        val adapter = FragmentPageAdapter(supportFragmentManager, lifecycle)
        viewPager2.adapter = adapter

        adventure.setOnClickListener(this)
        business.setOnClickListener(this)

        biztext = findViewById(R.id.business_textview)
        adtext = findViewById(R.id.adventure_textview)
        bizcolor = business.textColors.defaultColor
        adcolor = adventure.textColors.defaultColor

        adventure.performClick()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomnavigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    val intent = Intent(this, Classify::class.java)
                    startActivityWithAnimation(intent, R.anim.swipe_right_enter, R.anim.swipe_right_exit)
                    finish()
                    true
                }
                R.id.action_event -> {
                    true
                }
                R.id.action_recommendation -> {
                    val intent = Intent(this, Recommendations::class.java)
                    startActivityWithAnimation(intent, R.anim.swipe_left_enter, R.anim.swipe_left_exit)
                    true
                }
                else -> false
            }
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.adventure) {
            val size = adventure.width.toFloat()
            adtext.animate().x(size).duration = 150
            biztext.animate().x(0f).duration = 150
            biztext.setBackgroundColor(ContextCompat.getColor(this, R.color.default_tab))
            adventure.setTextColor(ContextCompat.getColor(this, R.color.adventure_red))
            business.setTextColor(bizcolor!!)
            viewPager2.setCurrentItem(0, true)
        } else if (view.id == R.id.business) {
            val size = adventure.width.toFloat()
            adtext.animate().x(0f).duration = 150
            biztext.animate().x(size).duration = 150
            biztext.setBackgroundColor(ContextCompat.getColor(this, R.color.default_tab))
            adventure.setTextColor(adcolor!!)// Sample Codes for the Event Screen

// Add actual code here
